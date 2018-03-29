import java.util.NoSuchElementException;

public class BracketPairList {
	private List<BracketPair> list;
	
	public BracketPairList()
	{
		this.list = new List<BracketPair>();
	}
	
	public boolean isValidOpener(String s)
	{
		try {
			return this.list.find(s) > 0 ? true:false;
		} catch(NoSuchElementException ex) {
			return false;
		}
	}
	
	public BracketPair findByOpener(String s)
	{
		int index = this.list.find(s);
		return this.list.get(index);
	}
	
	public void registerPair(BracketPair bp)
	{
		this.list.addFirst(bp);
	}
}
