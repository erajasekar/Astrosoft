/**
 * AstrosoftFileFilter.java
 * Created On 2006, May 26, 2006 7:44:43 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

import javax.swing.filechooser.FileFilter;

public class AstrosoftFileFilter extends FileFilter {

	public static final AstrosoftFileFilter HOROSCOPE_EXTN = new AstrosoftFileFilter(".ash", "AstroSoft Horoscopes (*.ash)");

	public static final AstrosoftFileFilter PDF_EXTN = new AstrosoftFileFilter(".pdf", "PDF Documents (*.pdf)");

	public static final AstrosoftFileFilter COMPACTIBILITY_EXTN = new AstrosoftFileFilter(".asc", "AstroSoft Compactibility (*.asc)");

	public static final AstrosoftFileFilter ALL_FILES = new AstrosoftFileFilter("*", "All Files (*.*)");

	private String extension;

	private String desc;

    public AstrosoftFileFilter(String extension, String desc) {
    	this.extension = extension;
    	this.desc = desc;
    }

    public boolean accept( java.io.File file ) {

        if ( file.isDirectory() || extension.equals("*") ) {

            return true;
        } else {

            return ( file.getName(  ) ).endsWith( extension );
        }

    }

    public String getDescription(  ) {

        return desc;

    }

    public String getExtension() {
		return extension;
	}

}

