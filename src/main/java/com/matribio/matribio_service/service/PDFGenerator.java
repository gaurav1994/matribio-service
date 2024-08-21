package com.matribio.matribio_service.service;


import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.matribio.matribio_service.entity.FamilyDetails;
import com.matribio.matribio_service.entity.FieldAttribute;
import com.matribio.matribio_service.entity.PersonalDetails;
import com.matribio.matribio_service.entity.UserBiodata;

public class PDFGenerator {
    private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	
	public static ByteArrayInputStream customerPDFReport(int n) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Customer Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(3);
        	// Add PDF Table Header ->
			Stream.of("ID", "First Name", "Last Name")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (int i=1;i<=n;i++ ) {
            	PdfPCell idCell = new PdfPCell(new Phrase("12355"));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase("Gaurav "+ i));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf("Singh "+ i)));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}

    public static ByteArrayInputStream bioDataPDFReport(UserBiodata userBiodata) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.CYAN);
			Paragraph paragraphPersonalDetails = new Paragraph( "Personal Details", font);
			paragraphPersonalDetails.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraphPersonalDetails);
			document.add(Chunk.NEWLINE);
        	PdfPTable table = new PdfPTable(3);
			
			// Map<String, String> personalDetailsMap = new LinkedHashMap<>();
            PersonalDetails personalDetails = userBiodata.getPersonalDetails();
			// copyPersonalDetailsToMap(personalDetailsMap, personalDetails);

            setPdfAttributeKey(table, "Name");
            setPdfAttributeValue(table, personalDetails.getFirstName());
			setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Place of birth");
            setPdfAttributeValue(table, personalDetails.getPlaceOfBirth());
			setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Date of birth");
            setPdfAttributeValue(table, personalDetails.getDateOfBirth().toString());
			setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Time of birth");
            setPdfAttributeValue(table, personalDetails.getTimeOfBirth().toString());
			setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Highest Qualification");
            setPdfAttributeValue(table, personalDetails.getHighestQualification());
			setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Work");
            setPdfAttributeValue(table, personalDetails.getWork());
			setPdfAttributeValue(table, null);

            document.add(table);
			document.add(Chunk.NEWLINE);
			Paragraph paragraphFamilyDetails = new Paragraph( "Family Details", font);
			paragraphFamilyDetails.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraphFamilyDetails);
			document.add(Chunk.NEWLINE);

			PdfPTable familyDetailsTable = new PdfPTable(2);
			FamilyDetails familyDetails = userBiodata.getFamilyDetails();

			setPdfAttributeKey(familyDetailsTable, "Father Name");
            setPdfAttributeValue(familyDetailsTable, familyDetails.getFatherName());

			setPdfAttributeKey(familyDetailsTable, "Mother Name");
            setPdfAttributeValue(familyDetailsTable, familyDetails.getMotherName());

			List<FieldAttribute> otherFamilyInformation = familyDetails.getOtherFamilyInformation();
			otherFamilyInformation.forEach(eachOtherFamilyInfo -> {
				setPdfAttributeKey(familyDetailsTable, eachOtherFamilyInfo.getFieldKey());
				setPdfAttributeValue(familyDetailsTable, eachOtherFamilyInfo.getFieldValue());
			});

			document.add(familyDetailsTable);

            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}

	private static void setPdfAttributeKey(PdfPTable table, String attributeKey ) {
		PdfPCell keyAttr = new PdfPCell(new Phrase(attributeKey));
		keyAttr.setPaddingLeft(2);
		keyAttr.setVerticalAlignment(Element.ALIGN_MIDDLE);
		keyAttr.setHorizontalAlignment(Element.ALIGN_LEFT);
		keyAttr.setFixedHeight(20.0f);
		// keyAttr.setBorder(0);
		table.addCell(keyAttr);
	}

	private static void setPdfAttributeValue(PdfPTable table, String attributeValue) {
		PdfPCell keyValue = new PdfPCell(new Phrase(attributeValue));
		keyValue.setPaddingLeft(4);
		keyValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
		keyValue.setHorizontalAlignment(Element.ALIGN_LEFT);
		keyValue.setFixedHeight(30.0f);
		// keyValue.setBorder(0);
		table.addCell(keyValue);
	}
    
}
