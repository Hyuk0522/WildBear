package Test;

import javax.swing.ImageIcon;

public class Item {
	private ImageIcon itemIcon = new ImageIcon("src\\img\\아이템1.png"); // 아이템 이미지 받아오기
	
	 private int i_iconWidth = itemIcon.getIconWidth(); // 받아온 아이템 이미지의 넓이
     private int i_iconHeight = itemIcon.getIconHeight();// 아이템 이미지 높이
     private int itemX=800; // 아이템의 초기 X 좌표 = 스타트 지점
     private int itemY = 200; // 아이템의 초기 Y 좌표
 	private int itemWidth = 72; // 아이템의 넓이
 	private int itemHeight = 72; // 아이템의 높이

 
 	
 	
	public int getI_iconWidth() {
		return i_iconWidth;
	}
	public void setI_iconWidth(int i_iconWidth) {
		this.i_iconWidth = i_iconWidth;
	}
	public int getI_iconHeight() {
		return i_iconHeight;
	}
	public void setI_iconHeight(int i_iconHeight) {
		this.i_iconHeight = i_iconHeight;
	}
	
	public ImageIcon getItemIcon() {
		return itemIcon;
	}
	public void setItemIcon(ImageIcon itemIcon) {
		this.itemIcon = itemIcon;
	}
	public int getIt_iconWidth() {
		return i_iconWidth;
	}
	public void setIt_iconWidth(int i_iconWidth) {
		this.i_iconWidth = i_iconWidth;
	}
	public int getIt_iconHeight() {
		return i_iconHeight;
	}
	public void setIt_iconHeight(int i_iconHeight) {
		this.i_iconHeight = i_iconHeight;
	}
	public int getItemX() {
		return itemX;
	}
	public void setItemX(int itemX) {
		this.itemX = itemX;
	}
	public int getItemY() {
		return itemY;
	}
	public void setItemY(int itemY) {
		this.itemY = itemY;
	}
	public int getItemWidth() {
		return itemWidth;
	}
	public void setItemWidth(int itemWidth) {
		this.itemWidth = itemWidth;
	}
	public int getItemHeight() {
		return itemHeight;
	}
	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
	}
	
	
	
}
