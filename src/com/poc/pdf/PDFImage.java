package com.poc.pdf;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFImage {
	
	public static void main(String [] args) throws URISyntaxException, IOException {
	
	PDFImage pdfImage = new PDFImage();
	
	pdfImage.insertImage();
	
	}
	
	public void insertImage() throws URISyntaxException, IOException {
				
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
	
		// Image for PDF
		Path path = Paths.get(ClassLoader.getSystemResource("ft.png").toURI());
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		PDImageXObject image = PDImageXObject.createFromFile(path.toAbsolutePath().toString(), document);
		contentStream.drawImage(image, 180, 590);
		
		// Heading to PDF
		contentStream.beginText();
		
		contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
		contentStream.setLeading(16.5f);

		contentStream.newLineAtOffset(225, 640);
		
		String lineheading = "Signature Document";
		
        contentStream.showText(lineheading);

		contentStream.endText();
		
		// Text for PDF
		contentStream.beginText();
			
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.setLeading(16.5f);

		contentStream.newLineAtOffset(84, 600);
        String line1 = "World War II (often abbreviated to WWII or WW2), "
                + "also known as the Second World War,";
        contentStream.showText(line1);

        contentStream.newLine();

        String line2 = "was a global war that lasted from 1939 to 1945, "
                + "although related conflicts began earlier.";
        contentStream.showText(line2);
        contentStream.newLine();

        String line3 = "It involved the vast majority of the world's "
                + "countries—including all of the great powers—";
        contentStream.showText(line3);
        contentStream.newLine();

        String line4 = "eventually forming two opposing military "
                + "alliances: the Allies and the Axis.";
        contentStream.showText(line4);
        contentStream.newLine();

        contentStream.endText();
				
		contentStream.close();
	
		document.save("resources/pdfBoxImage.pdf");
		document.close();
		
	}
	
	
	

}
