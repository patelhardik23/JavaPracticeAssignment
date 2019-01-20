import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderJava {
	public static void main(String... args) {
		List<Book> books = readBooksFromCSV("/home/hardikpatel/TEST/book.csv");

		for (Book b : books) {
			System.out.println(b);
		}

	}

	private static List<Book> readBooksFromCSV(String fileName) {
		List<Book> books = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			int iteration = 0;
			while (line != null) {
				if (iteration == 0) {
					iteration++;
					line = br.readLine();
					continue;
				}
				String[] attributes = line.split(",");
				Book book = createBook(attributes);
				books.add(book);
				line = br.readLine();

			}

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return books;
	}

	private static Book createBook(String[] metadata) {
		String name = metadata[0];
		int price = Integer.parseInt(metadata[1]);
		String author = metadata[2];

		return new Book(name, price, author);
	}
}

class Book {
	private String name;
	private int price;
	private String author;

	public Book(String name, int price, String author) {
		this.name = name;
		this.price = price;
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + ", author=" + author + "]";
	}

}