package myutil;

public class FileSizeFormatter {
	public static String formatSize(long size) {
		if (size <= 0)
			return "0 B";
		final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new java.text.DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " "
				+ units[digitGroups];
	}
}
//ssh -R 80:localhost:8080 serveo.net

