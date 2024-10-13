package Test;

import javax.swing.ImageIcon;

public class Player {

	 private ImageIcon playerIcon = new ImageIcon("src\\img\\곰돌이.png"); // 플레이어 캐릭터 받아오기

	 private int p_iconWidth = playerIcon.getIconWidth(); // 받아온 캐릭터 이미지 넓이  
     private int p_iconHeight = playerIcon.getIconHeight(); // 캐릭터 이미지 높이
	
	private int playerX = 200; // 초기 플레이어의 X 좌표
	private int playerY = 300; // 초기 플레이어의 Y 좌표
	private int playerWidth = 70; // 캐릭터의 넓이
	private int playerHeight = 70; // 캐릭터의 높이
	public ImageIcon getPlayerIcon() {
		return playerIcon;
	}
	public void setPlayerIcon(ImageIcon playerIcon) {
		this.playerIcon = playerIcon;
	}
	public int getP_iconWidth() {
		return p_iconWidth;
	}
	public void setP_iconWidth(int p_iconWidth) {
		this.p_iconWidth = p_iconWidth;
	}
	public int getP_iconHeight() {
		return p_iconHeight;
	}
	public void setP_iconHeight(int p_iconHeight) {
		this.p_iconHeight = p_iconHeight;
	}
	public int getPlayerX() {
		return playerX;
	}
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
	public int getPlayerWidth() {
		return playerWidth;
	}
	public void setPlayerWidth(int playerWidth) {
		this.playerWidth = playerWidth;
	}
	public int getPlayerHeight() {
		return playerHeight;
	}
	public void setPlayerHeight(int playerHeight) {
		this.playerHeight = playerHeight;
	}
	
	
	
	
}

