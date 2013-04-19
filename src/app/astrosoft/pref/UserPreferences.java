package app.astrosoft.pref;

import java.io.*;
import java.util.*;
import java.util.prefs.*;

/**
 * An implementation of Preferences. There are issues with the default implementation
 * provided by Java, such as throwing endless exceptions due to invalid assumptions
 * about file permissions, this implementation does not have those problems. 
 * <p>
 * Nodes are named using a / separated naming convention. The preferences are
 * stored in a tree-like structure, with the root node named "" per the
 * preferences specification. A node directly below the root node would be 
 * referenced by a / followed by the node name, for example "/node0". Node below
 * the initial child nodes follow the same convention, so "/node0/child0/kid2"
 * references a node 3 levels below the root node.
 * <p>
 * To store a value, call one of the various <code>put</code> methods. For example,
 * to store a username, you might call <code>put("/myapp/userinfo/username", username)</code>.
 * To get the value, call one of the various <code>get</code> methods. For example,
 * to get the username, you might call <code>get("/myapp/userinfor/username", "")</code>.
 * <p>
 * Nodes are stored in $user.home/.ise_prefs. In this implementation, system nodes
 * are the same as user nodes.
 * @author Dale Anson, Jan 2004
 */
public class UserPreferences extends AbstractPreferences {

   private Hashtable root;
   private Hashtable children;
   private boolean isRemoved = false;

   /**
    * Makes a node with <code>prefs</code> as parent and <code>name</code> as
    * the name of this node. As per the Preferences spec, if <code>prefs</code>
    * is null and <code>name</code> is "", this node will be a root node.
    * @param prefs parent preferences
    * @param name name for this node
    */
   public UserPreferences( UserPreferences prefs, String name ) {
      super( prefs, name );
      root = new Hashtable();
      children = new Hashtable();
      try {
         sync();
      }
      catch ( Exception e ) {
         e.printStackTrace();
      }
   }

   protected void putSpi( String key, String value ) {
      root.put( key, value );
   }

   protected String getSpi( String key ) {
      return ( String ) root.get( key );
   }

   protected void removeSpi( String key ) {
      root.remove( key );
   }

   protected void removeNodeSpi() throws BackingStoreException {
      isRemoved = true;
   }

   protected String[] keysSpi() throws BackingStoreException {
      return ( String[] ) root.keySet().toArray( new String[] {} );
   }

   protected String[] childrenNamesSpi() throws BackingStoreException {
      return ( String[] ) children.keySet().toArray( new String[] {} );
   }

   protected AbstractPreferences childSpi( String name ) {
      UserPreferences child = ( UserPreferences ) children.get( name );
      if ( child == null || child.isRemoved() ) {
         try {
            child = new UserPreferences( this, name );
            children.put( name, child );
         }
         catch ( Exception e ) {
            e.printStackTrace();
            child = null;
         }
      }
      return child;
   }

   /**
    * Returns the directory in which this node stores values, creating the directory
    * and its parents if necessary.
    * @return the directory in which this node stores values.
    */
   protected File getDirectory() {
      try {
         String name = name();
         if ( name == null || name.equals( "" ) )
            name = "root";
         File dir;
         if ( parent() == null )
            dir = UserPreferencesFactory.PREFS_ROOT;
         else
            dir = ( ( UserPreferences ) parent() ).getDirectory();
         File my_dir = new File( dir, name );
         my_dir.mkdirs();
         return my_dir;
      }
      catch ( Exception e ) {
         //e.printStackTrace();
         return null;
      }
   }

   protected void syncSpi() throws BackingStoreException {
      // no-op
   }

   public void sync() throws BackingStoreException {
      try {
         if ( isRemoved() ) {
            parent().sync();
            return ;
         }
         if ( root == null ) {
            root = new Hashtable();
         }
         File f = new File( getDirectory(), "prefs" );
         if ( !f.exists() )
            return ;
         ObjectInputStream decoder = new ObjectInputStream( new FileInputStream( f ) );
         Hashtable map = ( Hashtable ) decoder.readObject();
         decoder.close();
         root.putAll( map );
      }
      catch ( Exception e ) {
         //e.printStackTrace();
      }
   }

   public void flushSpi() throws BackingStoreException {
      try {
         if ( root == null || root.size() == 0 ) {
            return ;
         }
         if ( isRemoved() ) {
            parent().flush();
            return ;
         }
         File dir = getDirectory();
         if ( dir == null )
            throw new BackingStoreException( "Can't open directory." );
         File f = new File( dir, "prefs" );
         ObjectOutputStream encoder = new ObjectOutputStream( new FileOutputStream( f ) );
         encoder.writeObject( root );
         encoder.close();
      }
      catch ( BackingStoreException bse ) {
         throw bse;
      }
      catch ( Exception e ) {
         // ignore
      }
   }

   protected boolean isRemoved() {
      return isRemoved;
   }
}




