package resume;

import lombok.Data;

@Data
public class ResumeDTO {
	// Data Model
	int key;
	//basic
	String email;
	String name;
	String mname;
	String ename;
	String birth;
	String phone;
	String zipcode;
	String roadAdd;
	String jibunAdd;
	String detailAdd;
	String refAdd;
	//edu
	String eduName;
	String eduClass;
	String eduEnt;
	String eduGrad;
	String eduGradEn;
	//exp
	String expLoc;
	String expRank;
	String expResp;
	String expSWork;
	String expEWork;
	//qua
	String quaDate;
	String quaName;
	String quaRank;
	String quaAppr;
	//hope
	String hopeJob;
	String hopePay;
	String hopeLoc;
	String hopePres;
	//ext
	String extDate;
	String extContent;
	String extName;
	String extEct;
}
