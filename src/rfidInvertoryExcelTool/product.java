package rfidInvertoryExcelTool;

public class product {
	private String MasterKey;
	private String UPC;
	private String EPC;
	private String Style;
	private String Color;
	private String Size;
	private String Make;
	/**
	 * @return the masterKey
	 */
	public product(String MasterKey,String UPC,String EPC,String Style,String Color,String Size,String Make){
		this.MasterKey = MasterKey;
		this.UPC = UPC;
		this.EPC = EPC;
		this.Style = Style;
		this.Color = Color;
		this.Size = Size;
		this.Make = Make;
	}
	public product(){
		
	}
	
	public String getMasterKey() {
		return MasterKey;
	}
	/**
	 * @param masterKey the masterKey to set
	 */
	public void setMasterKey(String masterKey) {
		MasterKey = masterKey;
	}
	/**
	 * @return the uPC
	 */
	public String getUPC() {
		return UPC;
	}
	/**
	 * @param uPC the uPC to set
	 */
	public void setUPC(String uPC) {
		UPC = uPC;
	}
	/**
	 * @return the ePC
	 */
	public String getEPC() {
		return EPC;
	}
	/**
	 * @param ePC the ePC to set
	 */
	public void setEPC(String ePC) {
		EPC = ePC;
	}
	/**
	 * @return the style
	 */
	public String getStyle() {
		return Style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		Style = style;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return Color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		Color = color;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return Size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		Size = size;
	}
	/**
	 * @return the make
	 */
	public String getMake() {
		return Make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		Make = make;
	}
	
}
