package com.ims.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ims.impl.ProcessOrderImpl;
import com.ims.impl.StockInventoryImpl;



public class Billing {
	private ArrayList<String> items = new ArrayList<>(Arrays.asList("Clothes","Soap","Shampoo","Milk","Perfume","Chocolates","Handbag","Wallet","Bedsheet","Footware","HomeDecorPiece","Pen","Pencil"));
	private ArrayList<String> category = new ArrayList<>(Arrays.asList("Essentials","Essentials","Essentials","Essentials","Luxury","Luxury","Luxury","Luxury","Misc","Misc","Misc","Misc","Misc"));
	private ArrayList<String> quantity = new ArrayList<>(Arrays.asList("100","200","200","100","50","300","75","100","150","200","100","400","400"));
	private ArrayList<String> ppu = new ArrayList<>(Arrays.asList("20","5","10","5","50","3","150","100","75","25","40","3","3"));
	private ArrayList<String> cards = new ArrayList<>(Arrays.asList("5.41E+15","4.12E+12","3.41E+14","6.01E+15"));
	private HashMap<String,String> orderDetails = new HashMap<>();
	private String cardNumber = "";
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public static void main(String[] args) {
		
		StockInventoryImpl uniqueInventoryInstance = StockInventoryImpl.getInventoryInstance();
		
		Billing billing = new Billing();
		// set category cap
		billing.setCategoryCap(uniqueInventoryInstance);
		// set inventory
		billing.setInventory(uniqueInventoryInstance);
		// Add cards
		billing.setCards(uniqueInventoryInstance);
		int code  = 0;
		code = billing.readInput();
		switch(code) {
		
		case 111: System.out.println("Order Quantity is more than items in the inventory, please check error file for details");
		break;
		
		case 222: System.out.println("Order Quantity is more than category cap,please check error file for details");
		break;
		
		case 333: System.out.println("Order processed successfully, please check output file for final order price");
		break;
		
		case 444: System.out.println("Invalid card, unable to process order");
		break;
		
		case 555: System.out.println("Item name is blank, unable to process order");
		break;
		
		case 666: System.out.println("Item quantity is blank, unable to process order");
		break;
		
		case 777: System.out.println("Either order item name or quantity missing in order, unable to process order");
		break;
		
		case 999: System.out.println("Invalid file name, can't process order. Please try again");
		break;
		
		}
		

	}
	private void setInventory(StockInventoryImpl uniqueInventoryInstance) {
		for(int i=0;i<items.size();i++) {
		 uniqueInventoryInstance.addItem(items.get(i), category.get(i), quantity.get(i), ppu.get(i));
		}
		
	}
	public void setCategoryCap(StockInventoryImpl uniqueInventoryInstance) {
		uniqueInventoryInstance.addCap("Essentials", 3);
		uniqueInventoryInstance.addCap("Luxury", 4);
		uniqueInventoryInstance.addCap("Misc", 6);
		
	}
	public void setCards(StockInventoryImpl uniqueInventoryInstance) {
		for(int i=0;i<cards.size();i++) {
			 uniqueInventoryInstance.addCard(cards.get(i));
		}
	}
	
	public int readInput() {
		
		Scanner sc = new Scanner(System.in);
		// process order
		//enter file name after spaces
		int setOrderVal  = -1;
		int processOrderVal = -1;
		System.out.println("Enter order file name: ");
		String orderFilename = sc.next();
		if(orderFilename.isBlank() || orderFilename.isEmpty()) {
			sc.close();
			return 999;
		}
		if(orderFilename.contains(".xlsx") || orderFilename.contains(".xls")) {
			orderFilename = processxlsx(orderFilename);
		}
		setOrderVal = setOrder(orderFilename);
		if(setOrderVal != -1) {
			sc.close();
			return setOrderVal;
		}
		ProcessOrderImpl processOrder = new ProcessOrderImpl();
		processOrderVal = processOrder.processOrder(orderDetails, this.getCardNumber());
		sc.close();
		return processOrderVal;
	}
	public int setOrder(String orderFilename) {
		File order = null;
		String card  = null;
		order = new File(orderFilename);
		FileReader orderfileReader = null;
		try {
			orderfileReader = new FileReader(order);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader orderReader = new BufferedReader(orderfileReader);
		String orderline = "";
		String[] ordervalues = null;
		try {
			while((orderline = orderReader.readLine()) != null)
			{
				ordervalues = orderline.split("\n");
				for(int i=0;i< ordervalues.length;i++) {
					String[] orderitems = ordervalues[i].split(",");
					if( orderitems.length == 3) {
						if(!(orderitems[1].equals("Quantity"))) {
								String item = orderitems[0];
								String orderquantity = orderitems[1];
								card = orderitems[2];
								if(card.isBlank() || card.isEmpty())
									return 444;
								if(item.isBlank() || item.isEmpty())
									return 555;
								if(orderquantity.isBlank() || orderquantity.isEmpty())
									return 666;
								this.setCardNumber(card);
								orderDetails.put(item.toLowerCase(), orderquantity);
						}		
						
					}
					else if(orderitems.length == 2){
						
					
								String item = orderitems[0];
								String orderquantity = orderitems[1];
								if(item.isBlank() || item.isEmpty())
									return 555;
								if(orderquantity.isBlank() || orderquantity.isEmpty())
									return 666;
								orderDetails.put(item.toLowerCase(), orderquantity);
						
					}
					else if(orderitems.length == 1) {
						return 777;
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				orderReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return -1;
	}
	public String processxlsx(String inputFileName) {
        // For storing data into CSV files
        StringBuffer data = new StringBuffer();
        String outputFileName = "output.csv";
        File outputFile = new File(outputFileName);
        File inputFile = new File(inputFileName);
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            // Get the workbook object for XLSX file
            FileInputStream fis = new FileInputStream(inputFile);
            Workbook workbook = null;

            String ext = FilenameUtils.getExtension(inputFile.toString());

            if (ext.equalsIgnoreCase("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (ext.equalsIgnoreCase("xls")) {
                workbook = new HSSFWorkbook(fis);
            }

            // Get first sheet from the workbook

            int numberOfSheets = workbook.getNumberOfSheets();
            Row row;
            Cell cell;
            // Iterate through each rows from first sheet

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                while (rowIterator.hasNext()) {
                    row = rowIterator.next();
                    // For each row, iterate through each columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {

                        cell = cellIterator.next();
                        switch (cell.getCellTypeEnum()) {
                        case BOOLEAN:
                            data.append(cell.getBooleanCellValue() + ",");

                            break;
                        case NUMERIC:
                            data.append(cell.getNumericCellValue() + ",");

                            break;
                        case STRING:
                            data.append(cell.getStringCellValue() + ",");
                            break;

                        case BLANK:
                            data.append("" + ",");
                            break;
                        default:
                            data.append(cell + ",");

                        }
                    }
                    data.append('\n'); // appending new line after each row
                }

            }
            fos.write(data.toString().getBytes());
            fos.close();

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return outputFileName;
    }

}
