import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.IOException;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("Program has been started...");
        /*FirstLogging w = new FirstLogging();
        w.setTemperature(10);
        w.setTemperature(29);
        w.setTemperature(31);
        w.setTemperature(51);*/

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8884);

        // populate some data for the memory storage
        populateData();

        // Always add generic routes to the end
        get("/", (Request req, Response res) -> {
            logger.info("Webshop is displayed in a browser.");
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        // Equivalent with above
        get("/Category/:categoryName", (Request req, Response res) -> {
            logger.info("User is now on the category page.");
            return new ThymeleafTemplateEngine().render(ProductController.renderForCategory(req, res));
        });
//
        get("/Supplier/:supplierName", (Request req, Response res) -> {
            logger.info("User is now on the supplier page.");
            return new ThymeleafTemplateEngine().render(ProductController.renderForSupplier(req, res));
        });

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

    }


}
