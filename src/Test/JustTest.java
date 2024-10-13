package Test;

import javax.swing.ImageIcon;

public class JustTest {
	public static void main(String[] args) {
		ImageIcon playerIcon = new ImageIcon("src\\img\\곰돌이.png");

		int p_iconWidth = playerIcon.getIconWidth();
		int p_iconHeight = playerIcon.getIconHeight();

		ImageIcon itemIcon = new ImageIcon("src\\img\\아이템1.png");
		 int i_iconWidth = itemIcon.getIconWidth();
		 int i_iconHeight = itemIcon.getIconHeight();
		// TODO Auto-generated method stub
		System.out.println(p_iconWidth);
		System.out.println(p_iconHeight);
		System.out.println("================================");
		System.out.println(i_iconWidth);
		System.out.println(i_iconHeight);
		
		
	
	}

}
