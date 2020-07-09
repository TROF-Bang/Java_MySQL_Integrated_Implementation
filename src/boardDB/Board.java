package boardDB;

//JAVA Bean이라고 함.
public class Board {
	private int id;
    private String borderTitle;
    private String borderPassword;
    private String comboPublic;
    private String writerName;
    private String textContent;
	
    public Board() {
	
	}
    
    public Board(String borderTitle, String borderPassword, String comboPublic, String writerName, String textContent) {
		super();		
		this.borderTitle = borderTitle;
		this.borderPassword = borderPassword;
		this.comboPublic = comboPublic;
		this.writerName = writerName;
		this.textContent = textContent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBorderTitle() {
		return borderTitle;
	}

	public void setBorderTitle(String borderTitle) {
		this.borderTitle = borderTitle;
	}

	public String getBorderPassword() {
		return borderPassword;
	}

	public void setBorderPassword(String borderPassword) {
		this.borderPassword = borderPassword;
	}

	public String getComboPublic() {
		return comboPublic;
	}

	public void setComboPublic(String comboPublic) {
		this.comboPublic = comboPublic;
	}
	
	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}  
}
