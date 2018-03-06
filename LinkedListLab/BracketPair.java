
public class BracketPair {
	private String opener;
	private String closer;
	
	
	public BracketPair(String o, String c)
	{
		this.opener = o;
		this.closer = o;
	}
	
	public String getOpener()
	{
		return this.opener;
	}
	
	public String getCloser()
	{
		return this.closer;
	}
	
	public boolean isCloser(String c)
	{
		return this.closer.equals(c) ? true:false;
	}
}
