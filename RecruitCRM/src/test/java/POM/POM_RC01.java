package POM;

import org.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM_RC01 {

	public static WebDriver driver;

	@FindBy(xpath="//a[@class='menu-link candidate']")
	public WebElement sideBarCanditateIcon;
	
	@FindBy(xpath="//td/following::a[@class='media-a is-hidden-mobile']")
	public WebElement canditateName;
	
	@FindBy(xpath="//a[@*='sTest-editCandidateDetailsPage']/child::i[@class='material-icons']")
	public WebElement editIcon;
	
	@FindBy(id="sTest-candidateMobile")
	public WebElement txtBoxCanditateMob;

	@FindBy (id="sTest-candidateCity")
	public WebElement txtBoxCanditateCity;
	
	@FindBy (xpath="(//div[@class='dropdown-trigger'])[8]")
	public WebElement dropDownYearOfExp;
	
	public POM_RC01(WebDriver driver2) {

		this.driver=driver2;
		PageFactory.initElements(driver2, this);
	}

	public WebElement getSideBarCanditateIcon() {
		return sideBarCanditateIcon;
	}

	public void setSideBarCanditateIcon(WebElement sideBarCanditateIcon) {
		this.sideBarCanditateIcon = sideBarCanditateIcon;
	}

	public WebElement getCanditateName() {
		return canditateName;
	}

	public void setCanditateName(WebElement canditateName) {
		this.canditateName = canditateName;
	}

	public WebElement getEditIcon() {
		return editIcon;
	}

	public void setEditIcon(WebElement editIcon) {
		this.editIcon = editIcon;
	}

	public WebElement getTxtBoxCanditateMob() {
		return txtBoxCanditateMob;
	}

	public void setTxtBoxCanditateMob(WebElement txtBoxCanditateMob) {
		this.txtBoxCanditateMob = txtBoxCanditateMob;
	}

	public WebElement getTxtBoxCanditateCity() {
		return txtBoxCanditateCity;
	}

	public void setTxtBoxCanditateCity(WebElement txtBoxCanditateCity) {
		this.txtBoxCanditateCity = txtBoxCanditateCity;
	}

	public WebElement getDropDownYearOfExp() {
		return dropDownYearOfExp;
	}

	public void setDropDownYearOfExp(WebElement dropDownYearOfExp) {
		this.dropDownYearOfExp = dropDownYearOfExp;
	}

	public WebElement getDropDownYear_5() {
		return dropDownYear_5;
	}

	public void setDropDownYear_5(WebElement dropDownYear_5) {
		this.dropDownYear_5 = dropDownYear_5;
	}


	@FindBy (xpath="(//a[@class='button'])[3]/following::div[@class='dropdown-menu'][1]/child::div/child::a[contains(text(),'5')][1]")
	public WebElement dropDownYear_5;
	
	@FindBy(id="sTest-candidateAddBtn")
	public WebElement submitBtn;

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public void setSubmitBtn(WebElement submitBtn) {
		this.submitBtn = submitBtn;
	}
	
	
	
}
