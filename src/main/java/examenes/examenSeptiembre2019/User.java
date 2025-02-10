package examenes.examenSeptiembre2019;

public class User implements Comparable<User> {
	
	private String nick;
	private String provincia;
	
	public User(String nick) {
		this.nick = "";
		if(!nick.startsWith("@")) this.nick += "@";
		this.nick += nick;
	}
	
	public User(String nick, String provincia) {
		this.nick = "";
		if(!nick.startsWith("@")) this.nick += "@";
		this.nick += nick;
		this.provincia = provincia;
	}
	
	public String getNick() {
	    return this.nick;
	}
	
	@Override
	public String toString() {
		return this.nick + (this.provincia==null?"":(" <" + this.provincia + ">"));//...
	}
	
	@Override
	public boolean equals(Object o) {
		return this.compareTo((User)o) == 0;
	}
	@Override
	public int compareTo(User o) {
	//Orden: nick (less)
		return this.nick.toLowerCase().compareTo(o.nick.toLowerCase());
	}
}