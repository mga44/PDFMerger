import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mga44.io.FileChooserTask;
import mga44.pdf.PdfHelper;

public class Main {

	public static void main(String[] args) {
		initialize();

		Path srcPath = null;
		Path dstPath = null;
		if (args.length >= 2) {
			srcPath = Paths.get(args[0]);
			dstPath = Paths.get(args[1]);
		} else {
			try {
				FileChooserTask sourceAsker = new FileChooserTask("Please provide source directory");
				SwingUtilities.invokeAndWait(sourceAsker);
				FileChooserTask destinationAsker = new FileChooserTask("Please provide destination directory");
				SwingUtilities.invokeAndWait(destinationAsker);
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		}

		PdfHelper helper = new PdfHelper();
		try {
			helper.mergePdfs(srcPath, dstPath);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void initialize() {
		// TODO add logging to file
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
