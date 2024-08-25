package com.matribio.matribio_service.service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
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
			float [] pointColumnWidths = {150F, 150F};  
        	PdfPTable table = new PdfPTable(pointColumnWidths);
			
            PersonalDetails personalDetails = userBiodata.getPersonalDetails();
			setPdfAttributeKey(table, "");
			String imageFilePath = userBiodata.getProfilePicture();    
			if (imageFilePath != null) {
				Image imageFilePathInstance = Image.getInstance(imageFilePath);  
				PdfPCell imageProfileCell = new PdfPCell(imageFilePathInstance);
				imageProfileCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				imageProfileCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				imageProfileCell.setFixedHeight(250.0f);
				imageProfileCell.setBorder(0);
				table.addCell(imageProfileCell);
			}

            setPdfAttributeKey(table, "Name");
            setPdfAttributeValue(table, personalDetails.getFirstName());
			// setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Place of birth");
            setPdfAttributeValue(table, personalDetails.getPlaceOfBirth());
			// setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Date of birth");
            setPdfAttributeValue(table, personalDetails.getDateOfBirth() != null ? personalDetails.getDateOfBirth().toString() : "");
			// setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Time of birth");
            setPdfAttributeValue(table, personalDetails.getTimeOfBirth() != null ? personalDetails.getTimeOfBirth().toString(): "");
			// setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Highest Qualification");
            setPdfAttributeValue(table, personalDetails.getHighestQualification());
			// setPdfAttributeValue(table, null);

			setPdfAttributeKey(table, "Work");
            setPdfAttributeValue(table, personalDetails.getWork());
			// setPdfAttributeValue(table, null);

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
        }catch(DocumentException | IOException e) {
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
		keyAttr.setBorder(0);
		table.addCell(keyAttr);
	}

	private static void setPdfAttributeValue(PdfPTable table, String attributeValue) {
		PdfPCell keyValue = new PdfPCell(new Phrase(attributeValue));
		keyValue.setPaddingLeft(4);
		keyValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
		keyValue.setHorizontalAlignment(Element.ALIGN_LEFT);
		keyValue.setFixedHeight(30.0f);
		keyValue.setBorder(0);
		table.addCell(keyValue);
	}
    
}
