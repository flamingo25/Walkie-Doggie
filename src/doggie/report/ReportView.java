package doggie.report;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import doggie.animals.model.AnimalModel;
import doggie.animals.model.Compatibility;
import doggie.animals.model.Vaccination;
import doggie.user.model.User;
import doggie.user.model.UserProfile;

public class ReportView extends ReportOutput {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf\"");

		AnimalModel animal = (AnimalModel) model.get("animal");
		User user = (User) model.get("user");
		UserProfile profile = user.getUserProfile();
		List<Vaccination> vaccinations = (List<Vaccination>) model.get("vaccinations");
		List<Compatibility> compatibilities = (List<Compatibility>) model.get("compatibilities");
		
		Calendar cal = Calendar.getInstance();

		document.setPageSize(PageSize.A4);

		PdfPTable header = new PdfPTable(2);
		header.setWidths(new int[] { 50, 13 });
		header.setTotalWidth(527);
		header.setLockedWidth(true);
		header.getDefaultCell().setFixedHeight(60);
		header.getDefaultCell().setBorder(Rectangle.BOTTOM);
		header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

		PdfPCell text = new PdfPCell();
		text.setPaddingTop(25);
		text.setPaddingLeft(10);
		text.setBorder(Rectangle.BOTTOM);
		text.setBorderColor(BaseColor.LIGHT_GRAY);
		text.addElement(new Phrase("Walkie-Doggie Adoptionsantrag", new Font(Font.FontFamily.HELVETICA, 20)));
		header.addCell(text);

		PdfPCell img = new PdfPCell();
		String imageUrl = "https://raw.githubusercontent.com/Celyca/Walkie-Doggie/master/WebContent/resources/BildKl.png";
		Image image = Image.getInstance(new URL(imageUrl));
		image.setAlignment(Element.ALIGN_RIGHT);
		img.addElement(image);
		img.setBorder(Rectangle.BOTTOM);
		img.setBorderColor(BaseColor.LIGHT_GRAY);

		header.addCell(img);
		document.add(header);

		Font font = new Font(Font.FontFamily.HELVETICA, 12);

		PdfPTable address = new PdfPTable(1);
		address.setTotalWidth(527);
		address.setLockedWidth(true);
		address.getDefaultCell().setFixedHeight(40);
		address.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell addressCell = new PdfPCell();
		addressCell.setBorder(Rectangle.NO_BORDER);
		addressCell.setPaddingTop(100);
		addressCell.setPaddingLeft(20);
		addressCell.addElement(new Phrase(profile.getFirstName() + " " + profile.getSurName(), font));
		addressCell.addElement(new Phrase(profile.getAddress(), font));
		addressCell.addElement(new Phrase(profile.getZip() + " " + profile.getCity(), font));

		Paragraph datePhrase = new Paragraph();
		datePhrase.add("Graz, " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "."
				+ cal.get(Calendar.YEAR));
		datePhrase.setAlignment(Element.ALIGN_RIGHT);
		addressCell.addElement(datePhrase);

		addressCell.addElement(new Phrase(" ", font));
		addressCell.addElement(new Phrase(" ", font));

		Paragraph subject = new Paragraph();
		subject.add("Adoptionsantrag des Users " + user.getUserName() + " für das Haustier " + animal.getName());
		Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
		subject.setFont(bold);
		subject.setPaddingTop(10);
		addressCell.addElement(subject);

		address.addCell(addressCell);
		document.add(address);

		PdfPTable main = new PdfPTable(1);
		main.setTotalWidth(527);
		main.setLockedWidth(true);
		main.getDefaultCell().setFixedHeight(40);
		main.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell mainCell = new PdfPCell();
		mainCell.setBorder(Rectangle.NO_BORDER);
		mainCell.setPaddingTop(30);
		mainCell.setPaddingLeft(20);
		mainCell.addElement(new Phrase(
				"Für den Fall, dass ich ein Jungtier adoptiere, welches nicht kastriert ist, habe ich dafür Sorge zu tragen, dass das Tier in einem vertraglich bestimmten Alter kastriert wird. Die Bestätigung des Tierarztes muss vorgelegt und die Kosten von mir selbst getragen werden.\r\n" + 
				"\r\n" +
				"Mit dem Absenden des Adoptionsantrages habe ich bestätigt, dass ich über den finanziellen und zeitlichen Aufwand informiert bin und weiß, welche Pflichten mit der Anschaffung eines Tieres auf mich zukommen.\r\n" + 
				"",
				font));
		main.addCell(mainCell);
		document.add(main);
		
		PdfPTable animalValues = new PdfPTable(4);
		animalValues.setWidths(new int[] { 12, 20, 15, 15 });
		animalValues.setTotalWidth(527);
		animalValues.setLockedWidth(true);
		animalValues.getDefaultCell().setFixedHeight(40);
		animalValues.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell animal1Cell = new PdfPCell();
		animal1Cell.setBorder(Rectangle.NO_BORDER);
		animal1Cell.setPaddingTop(30);
		animal1Cell.setPaddingLeft(20);
		animal1Cell.addElement(new Phrase("Id:", bold));
		animal1Cell.addElement(new Phrase("Name:", bold));
		animal1Cell.addElement(new Phrase("Rasse:", bold));
		animal1Cell.addElement(new Phrase("Farbe:", bold));
		animal1Cell.addElement(new Phrase("Alter:", bold));
		animal1Cell.addElement(new Phrase("Geschlecht:", bold));
		animal1Cell.addElement(new Phrase("Kastriert:", bold));
		animal1Cell.addElement(new Phrase("Gechipt:", bold));
		animal1Cell.addElement(new Phrase(" ", font));
		animal1Cell.addElement(new Phrase(" ", font));


		String castrated = null;
		if (animal.isCastrated()) castrated = "Ja"; else castrated = "Nein";
		String chipped = null;
		if (animal.isChipped()) chipped = "Ja"; else chipped = "Nein";
		
		PdfPCell animal2Cell = new PdfPCell();
		animal2Cell.setBorder(Rectangle.NO_BORDER);
		animal2Cell.setPaddingTop(30);
		animal2Cell.setPaddingLeft(20);
		animal2Cell.addElement(new Phrase(new Integer(animal.getId()).toString(), font));
		animal2Cell.addElement(new Phrase(animal.getName(), font));
		animal2Cell.addElement(new Phrase(animal.getBreed(), font));
		animal2Cell.addElement(new Phrase(animal.getColor(), font));
		animal2Cell.addElement(new Phrase(new Integer(animal.getAge()).toString(), font));
		animal2Cell.addElement(new Phrase(animal.getGender(), font));
		animal2Cell.addElement(new Phrase(castrated, font));
		animal2Cell.addElement(new Phrase(chipped, font));
		
		PdfPCell animal3Cell = new PdfPCell();
		animal3Cell.setBorder(Rectangle.NO_BORDER);
		animal3Cell.setPaddingTop(30);
		animal3Cell.setPaddingLeft(20);
		animal3Cell.addElement(new Phrase("Impfungen:", bold));
		
		for (Vaccination vaccination : vaccinations) {
			animal3Cell.addElement(new Phrase(vaccination.getName(), font));
		}
		
		animal3Cell.addElement(new Phrase("Name:", font));

		
		PdfPCell animal4Cell = new PdfPCell();
		animal4Cell.setBorder(Rectangle.NO_BORDER);
		animal4Cell.setPaddingTop(30);
		animal4Cell.setPaddingLeft(20);
		animal4Cell.addElement(new Phrase("Vertr.:", bold));

		for (Compatibility compatibility : compatibilities) {
			animal4Cell.addElement(new Phrase(compatibility.getName(), font));
		}

		animalValues.addCell(animal1Cell);
		animalValues.addCell(animal2Cell);
		animalValues.addCell(animal3Cell);
		animalValues.addCell(animal4Cell);
		document.add(animalValues);
		
		
		PdfPTable sign = new PdfPTable(1);
		sign.setTotalWidth(527);
		sign.setLockedWidth(true);
		sign.getDefaultCell().setFixedHeight(40);
		sign.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell signCell = new PdfPCell();
		signCell.setBorder(Rectangle.NO_BORDER);
		signCell.setPaddingLeft(20);
		signCell.setPaddingBottom(30);
		signCell.addElement(new Phrase("______________________________", font));
		signCell.addElement(new Phrase(profile.getFirstName() + " " + profile.getSurName() + ", " + profile.getCity(), font));


		sign.addCell(signCell);
		document.add(sign);
		
		

		PdfPTable footer = new PdfPTable(1);
		footer.setWidths(new int[] { 24 });
		footer.setTotalWidth(527);
		footer.setLockedWidth(true);
		footer.getDefaultCell().setFixedHeight(40);
		footer.getDefaultCell().setBorder(Rectangle.TOP);
		footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
		
		PdfPCell footerCell = new PdfPCell();
		footerCell.setBorder(Rectangle.TOP);
		footerCell.setBorderColor(BaseColor.LIGHT_GRAY);
		footerCell.setPaddingLeft(20);
		footerCell.addElement(new Phrase("\u00A9 Walkie-Doggie", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
		footer.addCell(footerCell);

		document.add(footer);

	}
}
