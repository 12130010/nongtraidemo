package com.ndtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NongTraiApplication 
//implements CommandLineRunner
{
	
	public static void main(String[] args) {
		SpringApplication.run(NongTraiApplication.class, args);
	}

//	public void run(String... arg0) throws Exception {
//		actionRepository.deleteAll();
//		unitRepository.deleteAll();
//		
//		Coordinates cordinate = new Coordinates();
//		cordinate.setLongitude("100.10");
//		cordinate.setLatitude("200.200");
//		
//		Action action = new Action();
//		action.setCoordinate(cordinate);
//		action.setActionType("cho_an");
//		action.setActor("nhuocquy");
//		action.setDate(new Date());
//		action.setImageLinks(Arrays.asList("a.img", "b.img"));
//		action.setNote("Cho heo ăn");
//		
//		action = actionRepository.save(action);
//		System.out.println(action);
//		
//		Unit lv1 = new ComplexUnit();
//		lv1.setAddress("Trang trai nuoi heo");
//		lv1.setCoordinate(cordinate);
//		lv1.setLevel(1);
//		lv1.setName("Trang trai nuoi heo");
//		lv1.setOwners(Arrays.asList("nhuocquy1", "nhuocquy2"));
//		
//		Unit lv2 = new ComplexUnit();
//		
//		lv1.addChild(lv2);
//		
//		lv2.setAddress("Khu nuôi heo rừng");
//		lv2.setCoordinate(cordinate);
//		lv2.setLevel(2);
//		lv2.setName("Khu nuôi heo rừng");
//		lv2.setOwners(Arrays.asList("nhuocquy3", "nhuocquy4"));
//		
//		Unit lv3 = new SingleUnit();
//		
//		lv2.addChild(lv3);
//		
//		lv3.setAddress("Chuồng nuôi heo rừng");
//		lv3.setCoordinate(cordinate);
//		lv3.setLevel(3);
//		lv3.setName("Chuồng nuôi heo rừng");
//		lv3.setOwners(Arrays.asList("nhuocquy5", "nhuocquy6"));
//		
//		SingleUnit lv3Single = (SingleUnit) lv3;
//		
//		List<Action> listAction = new ArrayList<Action>();
//		listAction.add(action);
//		
//		//seccond Action
//		cordinate = new Coordinates();
//		cordinate.setLongitude("300.30");
//		cordinate.setLatitude("400.400");
//		
//		action = new Action();
//		action.setCoordinate(cordinate);
//		action.setActionType("cho_tam");
//		action.setActor("nhuocquy");
//		action.setDate(new Date());
//		action.setImageLinks(Arrays.asList("a1.img", "b1.img"));
//		action.setNote("Cho heo tắm");
//		
//		lv3Single.setActions(listAction);
//		lv3Single.addAction(action);
//		
//		lv1 = unitRepository.save(lv1);
//		System.out.println(lv1);
		
//		System.out.println(unitRepository.findOne("59e1ee52fe16d20c3863f3ff"));
//	}
}
