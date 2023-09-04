package code;
import java.util.LinkedList;
import java.util.Iterator; 
import javax.swing.JPanel;
import java.util.Queue;
public class SubframeRecords {

//	public static LinkedList list; 
	public static Queue<JPanel> list=new LinkedList<JPanel>() ; 
	
	
	public static Queue getList(){
		return list; 
	}
}
