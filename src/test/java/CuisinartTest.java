import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CuisinartTest {
    WebDriver driver;
    public WebDriverWait wait;

    @BeforeAll
    @DisplayName("Setting up the test environment")
    public void setup() {
        driver = new ChromeDriver();
//if you want to use firefox
//        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    @DisplayName("Verify Basic Authentication for Cuisinart Stage Environment")
    public void testBasicAuth() throws InterruptedException {
        // Basic Authentication using credentials
        String username = "storefront";
        String password = "conair1";
        Thread.sleep(3000);
        String url = "https://" + username + ":" + password + "@stage.cuisinart.com";

//        String url = "https://stage.cuisinart.com/";
        driver.get(url);
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);
        assertTrue(pageTitle.contains("Cuisinart")); // Replace with actual expected title
    }
    @Test

    @DisplayName("Verify Navigation and Functionality on Cuisinart Home Page")
    public void testHomePage() throws InterruptedException {

        // Ensure basic authentication test is run before
        testBasicAuth();

        // Use Actions to interact with navigation bar
        Actions action = new Actions(driver);

        // List of main navigation items
        String[] navItems = {
                "Appliances", "Bakeware", "Cookware", "Cutlery",
                "Kitchen Tools & Gadgets", "Outdoors",
                "Parts & Accessories", "Support", "Sale"
        };

        // Loop through each navigation item and verify it is displayed
        for (String item : navItems) {
            WebElement navMenuItem = driver.findElement(By.linkText(item));
            assertTrue(navMenuItem.isDisplayed(), item + " menu should be displayed");

            // Optionally click or hover over each menu item to check if it is functional
            action.moveToElement(navMenuItem).click().perform();

            // Add a small wait to allow the page to react (optional, for debugging)
            Thread.sleep(500);
        }

        WebElement logoButton = driver.findElement(By.xpath("//img[@alt='Cuisinart']"));
        logoButton.click();
        Thread.sleep(1000);

        // 1. Click on "SHOP NOW" for TOA-70
        WebElement shopNowTOA70 = driver.findElement(By.xpath("//a[@href='https://stage.cuisinart.com/air-fryer-toaster-oven-with-grill/TOA-70.html']"));
        shopNowTOA70.click();
        Thread.sleep(3000);
        assertTrue(driver.getTitle().contains("Air Fryer Toaster Oven with Grill"), "Should navigate to TOA-70 page");
        driver.navigate().back();  // Navigate back to the previous page
        Thread.sleep(2000);
        Utils.scroll(driver, 100);  // Scroll a bit down

// 2. Click "SHOP ALL CATEGORIES"
        WebElement shopAllCategories = driver.findElement(By.xpath("//a[@href='https://stage.cuisinart.com/shopbycategory/']"));
        shopAllCategories.click();
        Thread.sleep(3000);
        assertTrue(driver.getTitle().contains("Category"), "Should navigate to Shop By Category page");
        driver.navigate().back();  // Navigate back to the previous page
        Thread.sleep(2000);
        Utils.scroll(driver, 100);  // Scroll a bit down

        // 3. Click "SHOP NOW" for AIR-160
        WebElement shopNowAIR160 = driver.findElement(By.xpath("//a[@href='https://stage.cuisinart.com/4.5-qt-basket-air-fryer/AIR-160.html']"));
        shopNowAIR160.click();
        Thread.sleep(3000);
        assertTrue(driver.getTitle().contains("Basket Air Fryer"), "Should navigate to AIR-160 page");
        driver.navigate().back();  // Navigate back to the previous page
        Thread.sleep(2000);
        Utils.scroll(driver, 100);  // Scroll a bit down

        // 4. Click "SHOP OUR PINK COLLECTION"
        WebElement shopPinkCollection = driver.findElement(By.xpath("//a[@href='https://stage.cuisinart.com/collections-and-gift-guides/pinkandproud.html']"));
        shopPinkCollection.click();
        Thread.sleep(3000);
        assertTrue(driver.getTitle().contains("Pink And Proud"), "Should navigate to Pink and Proud Products page");
        driver.navigate().back();  // Navigate back to the previous page
        Thread.sleep(2000);
        Utils.scroll(driver, 100);  // Scroll a bit down
//
        // 5. Click "SHOP NOW" for Coffee Center
        WebElement shopNowCoffeeCenter = driver.findElement(By.xpath("//a[@href='https://stage.cuisinart.com/coffee-center-barista-bar-4-in-1-coffeemaker---crystal-glam/SS-4N1SC.html']"));
        shopNowCoffeeCenter.click();
        Thread.sleep(3000);
        assertTrue(driver.getTitle().contains("Barista Bar"), "Should navigate to Coffee Center page");
        driver.navigate().back();  // Navigate back to the previous page
        Thread.sleep(2000);
        Utils.scroll(driver, 100); // Scroll a bit down

        // 6. Check "Next slide" and "Previous slide" buttons for the slider
        WebElement nextButton = driver.findElement(By.xpath("//div[@class='swiper-next-btn svg-icon icon-arrowNavigationRight']"));
        assertTrue(nextButton.isDisplayed(), "Next slide button should be visible");
        nextButton.click();
        Thread.sleep(2000);  // Wait for the slider to move

        WebElement prevButton = driver.findElement(By.xpath("//div[@class='swiper-prev-btn svg-icon icon-arrowNavigationLeft']"));
        assertTrue(prevButton.isDisplayed(), "Previous slide button should be visible");
        prevButton.click();
        Thread.sleep(2000);  // Wait for the slider to move


    }


    @Test

    @DisplayName("Test Footer Links Functionality")
    public void testFooterLinks() throws InterruptedException {

        // Ensure basic authentication test is run before
        testBasicAuth();

        Utils.scroll(driver, 500);
        // Locate all the links under "Additional Support"
        List<WebElement> additionalSupportLinks = driver.findElements(By.xpath("//div[@class='content-asset']//ul[@class='menu-footer']//a"));

        // Iterate over each link and check if it is working
        for (WebElement link : additionalSupportLinks) {
            String linkText = link.getText();  // Get link text for debugging
            String url = link.getAttribute("href");  // Get URL for verification

            // Click the link and validate the page
            link.click();
            Thread.sleep(3000);  // Wait for the page to load

            // Validate if the page loaded correctly by checking the title or some element
            Assertions.assertFalse(driver.getTitle().isEmpty(), "Page title should not be empty for " + linkText);

            // Optionally, print or log the URL and title for debugging purposes
            System.out.println("Visited: " + url + " | Page Title: " + driver.getTitle());

            // Navigate back to the previous page
            driver.navigate().back();
            Thread.sleep(2000);  // Wait for the page to load again

            // Find the footer links again after navigating back (due to DOM refresh)
            additionalSupportLinks = driver.findElements(By.xpath("//div[@class='content-asset']//ul[@class='menu-footer']//a"));
            WebElement aboutUsLink = driver.findElement(By.xpath("//a[@href='https://stage.cuisinart.com/support/about-us.html']"));
            aboutUsLink.click();
            Thread.sleep(2000);  // Wait for the page to load
            assertTrue(driver.getTitle().contains("About Us"), "Should navigate to About Us page");

            // Navigate back to the previous page
            driver.navigate().back();
            Thread.sleep(2000);

            // Check "Cuisinart Cares" link
            WebElement cuisinartCaresLink = driver.findElement(By.xpath("//a[@href='https://stage.cuisinart.com/support/cuisinart-cares.html']"));
            cuisinartCaresLink.click();
            Thread.sleep(2000);  // Wait for the page to load
            assertTrue(driver.getTitle().contains("Cuisinart Cares"), "Should navigate to Cuisinart Cares page");

            // Navigate back to the previous page
            driver.navigate().back();
            Thread.sleep(2000);

            // Check "Careers" link
            WebElement careersLink = driver.findElement(By.xpath("//a[@href='https://jobs.jobvite.com/conair/']"));
            careersLink.click();
            Thread.sleep(2000);  // Wait for the page to load
            assertTrue(driver.getTitle().contains("Jobvite"), "Should navigate to Careers page");

            // Navigate back to the previous page
            driver.navigate().back();
            Thread.sleep(2000);

            WebElement phoneNumberLink = driver.findElement(By.xpath("//a[@href='tel:1-800-726-0190']"));
            String phoneNumberText = phoneNumberLink.getText();
            assertEquals("1-800-726-0190", phoneNumberText, "Phone number should be correct");

            // Check "Product Support" link
            WebElement productSupportLink = driver.findElement(By.xpath("//a[@href='https://stage.cuisinart.com/support/product-assistance.html']"));
            productSupportLink.click();
            Thread.sleep(2000);  // Wait for the page to load
            assertTrue(driver.getTitle().contains("Product Assistance"), "Should navigate to Product Support page");

            // Navigate back to the previous page
            driver.navigate().back();
            Thread.sleep(2000);

            // Social Media Icons check
            List<WebElement> socialMediaIcons = driver.findElements(By.xpath("//div[@class='social-media-icons']//a"));

            // Iterate over each social media icon link and check if it is working
            for (WebElement icon : socialMediaIcons) {
                String iconText = icon.getAttribute("href");  // Get URL for verification

                // Click the icon link and validate the page
                icon.click();
                Thread.sleep(3000);  // Wait for the page to load

                // Validate if the page loaded correctly by checking the title
                Assertions.assertFalse(driver.getTitle().isEmpty(), "Page title should not be empty for social media link: " + iconText);

                // Optionally, print or log the URL and title for debugging purposes
                System.out.println("Visited Social Media Link: " + iconText + " | Page Title: " + driver.getTitle());

                // Navigate back to the previous page
                driver.navigate().back();
                Thread.sleep(2000);  // Wait for the page to load again

                // Find the social media icons again after navigating back (due to DOM refresh)
                socialMediaIcons = driver.findElements(By.xpath("//div[@class='social-media-icons']//a"));
            }


        }
    }

    //
    @Test
    @DisplayName("Test Category Landing Page Navigation")
    public void testCategoryLandingPage() throws InterruptedException {
        testBasicAuth(); // Assuming this method handles authentication
        Utils.scroll(driver, 200);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to Categories
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'SHOP ALL CATEGORIES')]"))).click();
        Utils.scroll(driver, 250);

        // Step 2: Click on Appliances category
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/')]"))).click();

        // Step 3: Click on Air Fryers subcategory
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/air-fryers/')]"))).click();
        Utils.scroll(driver, 500);



    }


    @Test
    @DisplayName("Test Product Detail Page Functionality")
    public void testProductDetailPage() throws InterruptedException {
        testBasicAuth(); // Assuming this method handles authentication
        Utils.scroll(driver, 200);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to Categories
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'SHOP ALL CATEGORIES')]"))).click();
        Utils.scroll(driver, 250);

        // Step 2: Click on Appliances category
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/')]"))).click();

        // Step 3: Click on Air Fryers subcategory
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/air-fryers/')]"))).click();
        Utils.scroll(driver, 500);

        driver.findElement(By.linkText("Air Fryer Toaster Oven with Grill")).click();


        WebElement imageElement = driver.findElement(By.cssSelector("img[alt='Air Fryer Toaster Oven with Grill']"));

        // Verify if the image is displayed
        Assertions.assertTrue(imageElement.isDisplayed(), "Image is not displayed.");


        // Verify price
        WebElement price = driver.findElement(By.cssSelector(".price")); // Update with actual CSS selector
        Assertions.assertEquals("$229.95", price.getText());

        // Validate product rating
        WebElement ratingElement = driver.findElement(By.cssSelector("div[itemprop='ratingValue']"));
        String actualRating = ratingElement.getText().trim();
        String expectedRating = "4.6";
        Assertions.assertEquals(expectedRating, actualRating, "The product rating does not match the expected value.");
//
        // Validate number of reviews
        WebElement reviewsElement = driver.findElement(By.cssSelector("div.bv_numReviews_text"));
        String actualReviews = reviewsElement.getText().replaceAll("[()]", "").trim(); // Remove parentheses
        String expectedReviews = "3397";
        Assertions.assertEquals(expectedReviews, actualReviews, "The number of reviews does not match the expected value.");
//
        WebElement addToCartButton = driver.findElement(By.cssSelector("button.add-to-cart"));
        addToCartButton.click();

        // Find the alert message (after it appears)
        WebElement alertMessage = driver.findElement(By.cssSelector("span.text-standard"));

        // Assert that the alert message is displayed and contains the correct text
        assertEquals("Product added to cart", alertMessage.getText(), "The alert message should show 'Product added to cart'");


//


    }


    @Test
    @DisplayName("Test My Account Page Functionality")
    public void myAccountPage() throws InterruptedException {
        testBasicAuth(); // Assuming this method handles authentication
        WebElement loginButton = driver.findElement(By.cssSelector("a.js-login-modal")); // This targets the <a> tag directly

        // Click the login button (which contains the SVG)
        loginButton.click();

        // Wait for the modal to be visible (optional, but recommended for stability)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-email")));

// Locate the email input field and enter the email
        WebElement emailField = driver.findElement(By.id("login-form-email"));
        emailField.sendKeys("xyz@gmail.com"); // Replace with your email

// Locate the password input field and enter the password
        WebElement passwordField = driver.findElement(By.id("login-form-password"));
        passwordField.sendKeys("Test@12345"); // Replace with your password
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Wait up to 20 seconds
        // Locate the Sign In button and click it
        WebElement signInButton = driver.findElement(By.cssSelector(".btn.btn-block.btn-primary.js-submit-button"));
        signInButton.click();


        WebElement userMessageToggle = driver.findElement(By.cssSelector("span.user-message.btn.dropdown-toggle"));
        userMessageToggle.click();  // Click the toggle to show the dropdown menu

        // Create an instance of Actions class
        Actions actions = new Actions(driver);

        // Press the down arrow 5 times
        for (int i = 0; i < 5; i++) {
            actions.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN).perform();
        }

        // Press Enter
        actions.sendKeys(org.openqa.selenium.Keys.ENTER).perform();


        loginButton = driver.findElement(By.cssSelector("a.js-login-modal")); // This targets the <a> tag directly

        // Click the login button (which contains the SVG)
        loginButton.click();

        // Wait for the modal to be visible (optional, but recommended for stability)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-email")));

        WebElement createAccountButton = driver.findElement(By.id("toggleCreateAccountLink"));

        // Click the button
        createAccountButton.click();

        Faker faker = new Faker();
        WebElement firstNameField = driver.findElement(By.id("registration-form-fname"));
        firstNameField.sendKeys(faker.name().firstName());

        // Fill Last Name
        WebElement lastNameField = driver.findElement(By.id("registration-form-lname"));
        lastNameField.sendKeys(faker.name().lastName());

        // Fill Phone Number
        WebElement phoneField = driver.findElement(By.id("registration-form-phone"));
        phoneField.sendKeys(generateValidPhoneNumber());

        // Fill Email
        emailField = driver.findElement(By.id("registration-form-email"));
        emailField.sendKeys(faker.internet().emailAddress());

        // Fill Confirm Email
        WebElement confirmEmailField = driver.findElement(By.id("registration-form-email-confirm"));
        confirmEmailField.sendKeys(emailField.getAttribute("value")); // Use the same email

        // Fill Password
        passwordField = driver.findElement(By.id("registration-form-password"));
        String password = generateValidPassword(); // Generate a random password
        passwordField.sendKeys(password);

        String l=password;

        // Fill Confirm Password
        WebElement confirmPasswordField = driver.findElement(By.id("registration-form-password-confirm"));
        confirmPasswordField.sendKeys(l); // Use the same password


        Utils.scroll(driver, 500);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        createAccountButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Create Account')]"))
        );

        // Click the button
        createAccountButton.click();




    }
    public static String generateValidPassword() {
        Faker faker = new Faker();

        // Generate a random password of at least 8 characters
        String password = faker.internet().password(8, 255, true, true);

        return password;
    }

    public static String generateValidPhoneNumber() {
        Faker faker = new Faker();

        // Generate a valid phone number matching the criteria
        String areaCode = String.valueOf(faker.number().numberBetween(200, 300)); // area code between 200-299
        String prefix = String.valueOf(faker.number().numberBetween(200, 1000)); // prefix between 200-999
        String lineNumber = String.valueOf(faker.number().numberBetween(1000, 10000)); // line number between 1000-9999

        return String.format("%s-%s-%s", areaCode, prefix, lineNumber);
    }

    //
//
    @Test
    @DisplayName("Test Cart Page Functionality")
    public void testCartPage() throws InterruptedException {

        testBasicAuth(); // Assuming this method handles authentication
        Utils.scroll(driver, 200);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to Categories
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'SHOP ALL CATEGORIES')]"))).click();
        Utils.scroll(driver, 250);

        // Step 2: Click on Appliances category
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/')]"))).click();

        // Step 3: Click on Air Fryers subcategory
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/air-fryers/')]"))).click();
        Utils.scroll(driver, 500);

        driver.findElement(By.linkText("Air Fryer Toaster Oven with Grill")).click();


        WebElement addToCartButton = driver.findElement(By.cssSelector("button.add-to-cart"));
        addToCartButton.click();

        // Find the alert message (after it appears)
        WebElement alertMessage = driver.findElement(By.cssSelector("span.text-standard"));

        // Assert that the alert message is displayed and contains the correct text
        assertEquals("Product added to cart", alertMessage.getText(), "The alert message should show 'Product added to cart'");

        WebElement minicartButton = driver.findElement(By.cssSelector("a.minicart-link"));

        // Click the minicart button
        minicartButton.click();

        Thread.sleep(3000);

        int initialQuantity = 1; // Set initial quantity

        double expectedTotal = 229.95; // Base price for validation

        try {
            // Step 1: Add product to cart
            driver.findElement(By.cssSelector("a[href='/air-fryer-toaster-oven-with-grill/TOA-70.html']")).click();
            driver.findElement(By.cssSelector("input[data-field='quantity']")).clear();
            driver.findElement(By.cssSelector("input[data-field='quantity']")).sendKeys(String.valueOf(initialQuantity));
            driver.findElement(By.cssSelector(".button-plus")).click(); // Click to add item

            // Step 2: Verify product is added to cart
            WebElement cartIcon = driver.findElement(By.cssSelector(".cart-icon")); // Selector for cart icon
            cartIcon.click(); // Open cart
            String productName = driver.findElement(By.cssSelector(".product-info .body-m-bold a")).getText();
            assertEquals("Air Fryer Toaster Oven with Grill", productName, "Product name mismatch!");

            // Step 3: Verify quantity adjustments
            WebElement quantityField = driver.findElement(By.cssSelector(".quantity-field"));
            assertEquals(String.valueOf(initialQuantity), quantityField.getAttribute("value"), "Quantity mismatch!");

            // Step 4: Check total calculation
            WebElement totalElement = driver.findElement(By.cssSelector(".line-item-pricing-info .unit-price .sales.original-price .value"));
            assertEquals(String.valueOf(expectedTotal), totalElement.getAttribute("content"), "Total price mismatch!");


            // Step 6: Validate coupon application
            String discountMessage = driver.findElement(By.cssSelector(".discount-message")).getText();
            assertTrue(discountMessage.contains("Coupon applied successfully"), "Coupon not applied!");

            // Step 7: Remove product from cart
            driver.findElement(By.cssSelector(".remove-btn")).click();
            // Optionally, confirm removal in the popup if applicable
            driver.findElement(By.cssSelector(".confirm-remove-btn")).click(); // Confirm removal button

            // Verify cart is empty or product is removed
            String emptyCartMessage = driver.findElement(By.cssSelector(".empty-cart-message")).getText();
            assertEquals("Your cart is empty.", emptyCartMessage, "Cart is not empty!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    @DisplayName("Test Shipping Information Submission Flow")
    public void testInformationShippingPage() throws InterruptedException {

        testBasicAuth(); // Assuming this method handles authentication
        Utils.scroll(driver, 200);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to Categories
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'SHOP ALL CATEGORIES')]"))).click();
        Utils.scroll(driver, 250);

        // Step 2: Click on Appliances category
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/')]"))).click();

        // Step 3: Click on Air Fryers subcategory
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/air-fryers/')]"))).click();
        Utils.scroll(driver, 500);

        driver.findElement(By.linkText("Air Fryer Toaster Oven with Grill")).click();

        WebElement addToCartButton = driver.findElement(By.cssSelector("button.add-to-cart"));
        addToCartButton.click();

        // Step 4: Navigate to minicart and checkout
        WebElement minicartButton = driver.findElement(By.cssSelector("a.minicart-link"));
        minicartButton.click();

        Thread.sleep(3000);

        // Step 5: Click on the "Checkout" button
        WebElement checkoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-block.checkout-btn"));
        checkoutButton.click();

        // Step 6: Enter email and continue as guest
        WebElement emailInput = driver.findElement(By.id("email-guest"));
        emailInput.sendKeys("xyz@gmail.com");
        WebElement continueButton = driver.findElement(By.cssSelector("button.btn.btn-primary.btn-block.submit-customer.ca-round-8"));
        continueButton.click();

        Utils.scroll(driver, 150);

        // Step 7: Fill in the shipping details form
        WebElement firstNameField = driver.findElement(By.id("shippingFirstNamedefault"));
        firstNameField.sendKeys("John");

        WebElement lastNameField = driver.findElement(By.id("shippingLastNamedefault"));
        lastNameField.sendKeys("Doe");

        WebElement address1Field = driver.findElement(By.id("shippingAddressOnedefault"));
        address1Field.sendKeys("123 Main St");

        WebElement address2Field = driver.findElement(By.id("shippingAddressTwodefault"));
        address2Field.sendKeys("Apt 4B");

        WebElement stateDropdown = driver.findElement(By.id("shippingStatedefault"));
        stateDropdown.sendKeys("California");

        WebElement cityField = driver.findElement(By.id("shippingAddressCitydefault"));
        cityField.sendKeys("Los Angeles");

        WebElement zipCodeField = driver.findElement(By.id("shippingZipCodedefault"));
        zipCodeField.sendKeys("90001");

        WebElement phoneNumberField = driver.findElement(By.id("shippingPhoneNumberdefault"));
        phoneNumberField.sendKeys("9896543210");

        Utils.scroll(driver, 300);

        // Step 8: Submit the form
        WebElement button = driver.findElement(By.id("form-submit"));
        button.click();

        Thread.sleep(1000);
// Step 6: Validate displayed shipping address fields and method
        WebElement displayedFirstName = driver.findElement(By.cssSelector(".address-summary .firstName"));
        WebElement displayedLastName = driver.findElement(By.cssSelector(".address-summary .lastName"));
        WebElement displayedAddress1 = driver.findElement(By.cssSelector(".address-summary .address1"));
        WebElement displayedAddress2 = driver.findElement(By.cssSelector(".address-summary .address2"));
        WebElement displayedCity = driver.findElement(By.cssSelector(".address-summary .city"));
        WebElement displayedState = driver.findElement(By.cssSelector(".address-summary .stateCode"));
        WebElement displayedPostalCode = driver.findElement(By.cssSelector(".address-summary .postalCode"));
        WebElement displayedPhone = driver.findElement(By.cssSelector(".shipping-phone"));
        WebElement displayedShippingMethod = driver.findElement(By.cssSelector(".shipping-method-title"));

        // Assertions to verify displayed values against the input
        assert displayedFirstName.getText().equals("John") : "First name mismatch!";
        assert displayedLastName.getText().equals("Doe") : "Last name mismatch!";
        assert displayedAddress1.getText().equals("123 Main St") : "Address line 1 mismatch!";
        assert displayedAddress2.getText().equals("Apt 4B") : "Address line 2 mismatch!";
        assert displayedCity.getText().equals("Los Angeles") : "City mismatch!";
        assert displayedState.getText().equals("CA") : "State code mismatch!";
        assert displayedPostalCode.getText().equals("90001") : "Postal code mismatch!";
        assert displayedPhone.getText().equals("9896543210") : "Phone number mismatch!";
        assert displayedShippingMethod.getText().equals("Standard Service") : "Shipping method mismatch!";


    }


    @Test
    @DisplayName("Test Shipping Page Functionality: Add Item to Cart and Complete Checkout")
    public void testShippingPage() throws InterruptedException {

        testBasicAuth(); // Assuming this method handles authentication
        Utils.scroll(driver, 200);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to Categories
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'SHOP ALL CATEGORIES')]"))).click();
        Utils.scroll(driver, 250);

        // Step 2: Click on Appliances category
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/')]"))).click();

        // Step 3: Click on Air Fryers subcategory
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/air-fryers/')]"))).click();
        Utils.scroll(driver, 500);

        driver.findElement(By.linkText("Air Fryer Toaster Oven with Grill")).click();

        WebElement addToCartButton = driver.findElement(By.cssSelector("button.add-to-cart"));
        addToCartButton.click();

        // Step 4: Navigate to minicart and checkout
        WebElement minicartButton = driver.findElement(By.cssSelector("a.minicart-link"));
        minicartButton.click();

        Thread.sleep(3000);

        // Step 5: Click on the "Checkout" button
        WebElement checkoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-block.checkout-btn"));
        checkoutButton.click();

        // Step 6: Enter email and continue as guest
        WebElement emailInput = driver.findElement(By.id("email-guest"));
        emailInput.sendKeys("xyz@gmail.com");
        WebElement continueButton = driver.findElement(By.cssSelector("button.btn.btn-primary.btn-block.submit-customer.ca-round-8"));
        continueButton.click();

        Utils.scroll(driver, 150);

        // Step 7: Fill in the shipping details form
        WebElement firstNameField = driver.findElement(By.id("shippingFirstNamedefault"));
        firstNameField.sendKeys("John");

        WebElement lastNameField = driver.findElement(By.id("shippingLastNamedefault"));
        lastNameField.sendKeys("Doe");

        WebElement address1Field = driver.findElement(By.id("shippingAddressOnedefault"));
        address1Field.sendKeys("123 Main St, Abercrombie ND 58001");

        WebElement address2Field = driver.findElement(By.id("shippingAddressTwodefault"));
        address2Field.sendKeys("Apt 4B");

        WebElement stateDropdown = driver.findElement(By.id("shippingStatedefault"));
        stateDropdown.sendKeys("California");

        WebElement cityField = driver.findElement(By.id("shippingAddressCitydefault"));
        cityField.sendKeys("Los Angeles");

        WebElement zipCodeField = driver.findElement(By.id("shippingZipCodedefault"));
        zipCodeField.sendKeys("90001");

        WebElement phoneNumberField = driver.findElement(By.id("shippingPhoneNumberdefault"));
        phoneNumberField.sendKeys("9876543210");

        Utils.scroll(driver, 400);

        Thread.sleep(3000);


//label[@for='shippingMethod-SDA-f816a1afd30887a7aa01e7a8ae']//span[@class='radio-inner']


//        Thread.sleep(6000);
//
//        // Wait for the cost to update
//        Thread.sleep(2000); // Use proper waiting conditions for production code
//
//        Utils.scroll(driver,-300);
//
//        // Verify the total shipping cost updates correctly
//        WebElement shippingTotalCost = driver.findElement(By.className("shipping-total-cost"));
//        String totalCostText = shippingTotalCost.getText();
//        Thread.sleep(2000); // Use proper waiting conditions for production code
//        // Assert that the shipping cost is $39.09 after selecting "Second Day Air"
//        assertEquals("$0", totalCostText);


    }


    @Test
    @DisplayName("Test payment process on the  site")
    public void testPaymentPage() throws InterruptedException {

        testBasicAuth(); // Assuming this method handles authentication
        Utils.scroll(driver, 200);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to Categories
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'SHOP ALL CATEGORIES')]"))).click();
        Utils.scroll(driver, 250);

        // Step 2: Click on Appliances category
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/')]"))).click();

        // Step 3: Click on Air Fryers subcategory
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/air-fryers/')]"))).click();
        Utils.scroll(driver, 500);

        driver.findElement(By.linkText("Air Fryer Toaster Oven with Grill")).click();

        WebElement addToCartButton = driver.findElement(By.cssSelector("button.add-to-cart"));
        addToCartButton.click();

        // Step 4: Navigate to minicart and checkout
        WebElement minicartButton = driver.findElement(By.cssSelector("a.minicart-link"));
        minicartButton.click();

        Thread.sleep(3000);

        // Step 5: Click on the "Checkout" button
        WebElement checkoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-block.checkout-btn"));
        checkoutButton.click();

        // Step 6: Enter email and continue as guest
        WebElement emailInput = driver.findElement(By.id("email-guest"));
        emailInput.sendKeys("xyz@gmail.com");
        WebElement continueButton = driver.findElement(By.cssSelector("button.btn.btn-primary.btn-block.submit-customer.ca-round-8"));
        continueButton.click();

        Utils.scroll(driver, 150);

        // Step 7: Fill in the shipping details form
        WebElement firstNameField = driver.findElement(By.id("shippingFirstNamedefault"));
        firstNameField.sendKeys("John");

        WebElement lastNameField = driver.findElement(By.id("shippingLastNamedefault"));
        lastNameField.sendKeys("Doe");

        WebElement address1Field = driver.findElement(By.id("shippingAddressOnedefault"));
        address1Field.sendKeys("123 Main St");

        WebElement address2Field = driver.findElement(By.id("shippingAddressTwodefault"));
        address2Field.sendKeys("Apt 4B");

        WebElement stateDropdown = driver.findElement(By.id("shippingStatedefault"));
        stateDropdown.sendKeys("California");

        WebElement cityField = driver.findElement(By.id("shippingAddressCitydefault"));
        cityField.sendKeys("Los Angeles");

        WebElement zipCodeField = driver.findElement(By.id("shippingZipCodedefault"));
        zipCodeField.sendKeys("90001");

        WebElement phoneNumberField = driver.findElement(By.id("shippingPhoneNumberdefault"));
        phoneNumberField.sendKeys("9896543210");

        Utils.scroll(driver, 300);

        // Step 8: Submit the form
        WebElement button = driver.findElement(By.id("form-submit"));
        button.click();

        Thread.sleep(1000);

        Utils.scroll(driver, 300);

        WebElement cardOwner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardOwner")));
        cardOwner.sendKeys("John Doe"); // Replace with the actual name you want to enter

        // Fill in the card number
        WebElement cardNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardNumber")));
        cardNumber.sendKeys("4111111111111111"); // Replace with a valid card number

        // Select expiration month
        Select expirationMonth = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expirationMonth"))));
        expirationMonth.selectByValue("12"); // Selecting December

        // Select expiration year
        Select expirationYear = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expirationYear"))));
        expirationYear.selectByValue("2026"); // Selecting the year 2026

        // Fill in the security code
        WebElement securityCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("securityCode")));
        securityCode.sendKeys("123"); // Replace with a valid security code

        Utils.scroll(driver, 500);
        Thread.sleep(3000);

        // Create WebDriverWait instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the button using XPath and wait for it to be clickable
        continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue' and contains(@class, 'submit-payment')]"))
        );

        // Click the button
        continueButton.click();


        // Create WebDriverWait instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Locate the button using XPath
        WebElement placeOrderButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-block place-order ca-round-8' and @name='submit' and @value='place-order']"))
        );

        // Click the button
        placeOrderButton.click();
//
//        // Create WebDriverWait instance
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Locate the button using XPath and click it
//        placeOrderButton = wait.until(
//                ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-block place-order ca-round-8' and @name='submit' and @value='place-order']"))
//        );
//
//        // Click the button
//        placeOrderButton.click();
//
//        // Wait for the elements to be visible and retrieve their values
//        WebElement subtotalElement = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='sub-total']"))
//        );
//        WebElement shippingCostElement = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='shipping-total-cost']"))
//        );
//        WebElement taxTotalElement = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tax-total']"))
//        );
//        WebElement grandTotalElement = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='grand-total-sum grand-total']"))
//        );
//
//        // Get the text of each element
//        String subtotalText = subtotalElement.getText();
//        String shippingCostText = shippingCostElement.getText();
//        String taxTotalText = taxTotalElement.getText();
//        String grandTotalText = grandTotalElement.getText();
//
//        // Assert the values
//        assertEquals("$229.95", subtotalText, "The sub-total value is incorrect.");
//        assertEquals("$0.00", shippingCostText, "The shipping cost value is incorrect.");
//        assertEquals("$22.50", taxTotalText, "The tax total value is incorrect.");
//        assertEquals("$252.45", grandTotalText, "The grand total value is incorrect.");
//
//
//        // Click on the "Place Order" button
//        placeOrderButton = wait.until(
//                ExpectedConditions.elementToBeClickable(By.className("place-order"))
//        );
//        placeOrderButton.click();
//
//        // Wait for the billing address section to be visible
//        WebElement billingAddressSection = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.className("billing-summary-details"))
//        );
////
//        // Retrieve and assert billing address details
//        assertEquals("John", billingAddressSection.findElement(By.className("firstName")).getText(), "First name is incorrect.");
//        assertEquals("Doe", billingAddressSection.findElement(By.className("lastName")).getText(), "Last name is incorrect.");
//        assertEquals("123 Main St", billingAddressSection.findElement(By.className("address1")).getText(), "Address 1 is incorrect.");
//        assertEquals("Apt 4B", billingAddressSection.findElement(By.className("address2")).getText(), "Address 2 is incorrect.");
//        assertEquals("Los Angeles", billingAddressSection.findElement(By.className("city")).getText(), "City is incorrect.");
//        assertEquals("CA", billingAddressSection.findElement(By.className("stateCode")).getText(), "State code is incorrect.");
//        assertEquals("90001", billingAddressSection.findElement(By.className("postalCode")).getText(), "Postal code is incorrect.");
//        assertEquals("xyz@gmail.com", billingAddressSection.findElement(By.className("order-summary-email")).getText(), "Email is incorrect.");
//        assertEquals("9896543210", billingAddressSection.findElement(By.className("order-summary-phone")).getText(), "Phone number is incorrect.");
//        Utils.scroll(driver, 500);


    }


    @Test
    @DisplayName("Test order confirmation on the  site")
    public void orderConfirmationPage() throws InterruptedException {

        testBasicAuth(); // Assuming this method handles authentication
        Utils.scroll(driver, 200);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to Categories
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'SHOP ALL CATEGORIES')]"))).click();
        Utils.scroll(driver, 250);

        // Step 2: Click on Appliances category
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/')]"))).click();

        // Step 3: Click on Air Fryers subcategory
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/shopping/appliances/air-fryers/')]"))).click();
        Utils.scroll(driver, 500);

        driver.findElement(By.linkText("Air Fryer Toaster Oven with Grill")).click();

        WebElement addToCartButton = driver.findElement(By.cssSelector("button.add-to-cart"));
        addToCartButton.click();

        // Step 4: Navigate to minicart and checkout
        WebElement minicartButton = driver.findElement(By.cssSelector("a.minicart-link"));
        minicartButton.click();

        Thread.sleep(3000);

        // Step 5: Click on the "Checkout" button
        WebElement checkoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-block.checkout-btn"));
        checkoutButton.click();

        // Step 6: Enter email and continue as guest
        WebElement emailInput = driver.findElement(By.id("email-guest"));
        emailInput.sendKeys("xyz@gmail.com");
        WebElement continueButton = driver.findElement(By.cssSelector("button.btn.btn-primary.btn-block.submit-customer.ca-round-8"));
        continueButton.click();

        Utils.scroll(driver, 150);

        // Step 7: Fill in the shipping details form
        WebElement firstNameField = driver.findElement(By.id("shippingFirstNamedefault"));
        firstNameField.sendKeys("John");

        WebElement lastNameField = driver.findElement(By.id("shippingLastNamedefault"));
        lastNameField.sendKeys("Doe");

        WebElement address1Field = driver.findElement(By.id("shippingAddressOnedefault"));
        address1Field.sendKeys("123 Main St");

        WebElement address2Field = driver.findElement(By.id("shippingAddressTwodefault"));
        address2Field.sendKeys("Apt 4B");

        WebElement stateDropdown = driver.findElement(By.id("shippingStatedefault"));
        stateDropdown.sendKeys("California");

        WebElement cityField = driver.findElement(By.id("shippingAddressCitydefault"));
        cityField.sendKeys("Los Angeles");

        WebElement zipCodeField = driver.findElement(By.id("shippingZipCodedefault"));
        zipCodeField.sendKeys("90001");

        WebElement phoneNumberField = driver.findElement(By.id("shippingPhoneNumberdefault"));
        phoneNumberField.sendKeys("9896543210");

        Utils.scroll(driver, 300);

        // Step 8: Submit the form
        WebElement button = driver.findElement(By.id("form-submit"));
        button.click();

        Thread.sleep(1000);

        Utils.scroll(driver, 300);

        WebElement cardOwner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardOwner")));
        cardOwner.sendKeys("John Doe"); // Replace with the actual name you want to enter

        // Fill in the card number
        WebElement cardNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardNumber")));
        cardNumber.sendKeys("4242 4242 4242 4242 "); // Replace with a valid card number

        // Select expiration month
        Select expirationMonth = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expirationMonth"))));
        expirationMonth.selectByValue("10"); // Selecting December

        // Select expiration year
        Select expirationYear = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expirationYear"))));
        expirationYear.selectByValue("2029"); // Selecting the year 2026

        // Fill in the security code
        WebElement securityCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("securityCode")));
        securityCode.sendKeys("385"); // Replace with a valid security code

        Utils.scroll(driver, 500);
        Thread.sleep(3000);

        // Create WebDriverWait instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the button using XPath and wait for it to be clickable
        continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue' and contains(@class, 'submit-payment')]"))
        );

        // Click the button
        continueButton.click();


        // Create WebDriverWait instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Locate the button using XPath
        WebElement placeOrderButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-block place-order ca-round-8' and @name='submit' and @value='place-order']"))
        );

        // Click the button
        placeOrderButton.click();



    }
    @AfterAll
    @DisplayName("Test Closed")
    public void closeDriver() {
        driver.quit(); // Properly close the browser after tests are complete
    }

    }








