package in.ineuron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import in.ineuron.service.IPurchaseOrder;
import in.ineuron.service.PurchaseOrderImpl;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		IPurchaseOrder purchaseOrder = context.getBean(PurchaseOrderImpl.class);
		
		
		String[] items = {"Fossil: Watch, US POLO: T-Shirt, Pepe Jeans: Trouser"};
		double[] prices = {12000.0, 4000, 3500};
		String[] emails = {"ritankr0425@gmail.com", "ritankar1999@gmail.com"};
		
		try {
			purchaseOrder.purchase(items, prices, emails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
