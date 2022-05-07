package mga44.pdf;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class PdfHelper {
	private static final String PDF = ".pdf";

	/**
	 * 
	 * @param source      directory containing documents to merge
	 * @param destination path representing merged file localization. If represents
	 *                    directory, appropriate file will be created
	 * @return object representing merged file
	 * @throws IOException
	 */
	public Path mergePdfs(Path source, Path destination) throws IOException {
		Objects.requireNonNull(source);
		Objects.requireNonNull(destination);

		PDFMergerUtility merger = new PDFMergerUtility();
		merger.setDestinationFileName(destination.toFile().getAbsolutePath());
		List<Path> files = Files.list(source).filter(x -> x.toFile().getName().endsWith(PDF))
				.collect(Collectors.toList());
		for (Path sourceItem : files)
			merger.addSource(sourceItem.toFile());

		merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

		return destination;
	}
}
