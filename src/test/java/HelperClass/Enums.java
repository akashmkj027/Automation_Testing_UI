package HelperClass;

public class Enums {
	
	 public enum FileType
     {
         XML,
         EXCEL,
         JSON
     };
     
     public enum OfferCatagory
     {
    	 PRODUCT_AND_SERVICES,
    	 PRODUCTS_ONLY,
    	 PRODUCT_SERVICE_MAPPING_ENTER_ITEM_NAME,
    	 PRODUCT_SERVICE_MAPPING_UPLOAD_ITEM_LIST,
    	 SUBSCRIPTION_ITEM,
    	 SUBSCRIPTION_MANDATORY,
    	 SERVICE_ITEMS
     };
     
     public enum MasterDbConnect
     {
    	 TS1DMP,
    	 TS3DMP,
    	 TS3CG1,
    	 DMSTG
     };
     
     public enum PriceListPriceFile
     {
    	 GLOBAL_PRICE_LIST_US,
    	 WHOLESALE_PRICE_LIST_US,
    	 DISTRIBUTION_PRICING_PLUS_US,
    	 DISTRIBUTION_PRICING_PLUS_AUSTRALIA,
    	 DISTRIBUTION_PRICING_PLUS_CANADA
     };
     
     public enum Catalog
     {
    	 GLOBAL_PRICE_LIST,
    	 DISTRIBUTION_PRICING
     };
     
     public enum Content
     {
    	 SEARCH_BY_PRODUCT,
    	 SEARCH_BY_PRODUCT_FAMILY
     };

}
