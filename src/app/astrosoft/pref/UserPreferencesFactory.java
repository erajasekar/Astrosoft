package app.astrosoft.pref;

import java.util.prefs.PreferencesFactory;
import java.util.prefs.Preferences;

import java.io.*;

/**
 * This preferences factory only works for a single user and won't be shared
 * across the jvm, that is, system preferences are the same as user preferences.
 * Preferences are stored in $user.home/.ise_prefs.
 */
public class UserPreferencesFactory implements PreferencesFactory {
   protected static File PREFS_ROOT;

   /**
    * System root node.
    */
   private static UserPreferences systemRoot = null;

   /**
    * User root node.
    */
   private static UserPreferences userRoot = null;


   public Preferences systemRoot() {
      return getSystemRoot();
   }

   public Preferences userRoot() {
      return getSystemRoot();
   }
   
   public static Preferences getSystemRoot() {
      if ( systemRoot == null ) {
         PREFS_ROOT = new File( System.getProperty( "user.home" ) + File.separator + ".astrosoft_prefs" );
         try {
            PREFS_ROOT.mkdirs();
         }
         catch ( Exception e ) {}
         systemRoot = new UserPreferences( null, "" );
      }
      return systemRoot;
   }
   
   public static Preferences getUserRoot() {
      return getSystemRoot();
   }
}




