package tools;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import restclient.dto.Product;

public class ProductsListCell extends ListCell<Product> {
    private static final String CACHE_LIST_FOUND_CLASS = "cache-list-found";
    private static final String CACHE_LIST_NOT_FOUND_CLASS = "cache-list-not-found";
    private static final String CACHE_LIST_NAME_CLASS = "cache-list-name";
    private static final String CACHE_LIST_DT_CLASS = "cache-list-dt";
    private static final String CACHE_LIST_ICON_CLASS = "cache-list-icon";
    private static final String FONT_AWESOME = "FontAwesome";

    private GridPane grid = new GridPane();
    private Label icon = new Label();
    private Label name = new Label();
    private Label price = new Label();
    private Label description = new Label();

    public ProductsListCell() {
        configureGrid();
        configureIcon();
        configureName();
        configureprice();
        addControlsToGrid();
    }

    private void configureGrid() {
        grid.setHgap(10);
        grid.setVgap(4);
        grid.setPadding(new Insets(0, 10, 0, 10));
    }

    private void configureIcon() {
        icon.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        icon.getStyleClass().add(CACHE_LIST_ICON_CLASS);
    }

    private void configureName() {
        name.getStyleClass().add(CACHE_LIST_NAME_CLASS);
    }

    private void configureprice() {
        price.getStyleClass().add(CACHE_LIST_DT_CLASS);
    }

    private void addControlsToGrid() {
        grid.add(icon, 0, 0, 1, 3);
        grid.add(name, 1, 0);
        grid.add(price, 1, 2);
        grid.add(description,1,1);
    }

    @Override
    public void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);
        if (empty) {
            clearContent();
        } else {
            addContent(product);
        }
    }

    private void clearContent() {
        setText(null);
        setGraphic(null);
    }

    private void addContent(Product product) {
        setText(null);
        icon.setText(GlyphsDude.createIcon(FontAwesomeIcon.valueOf(product.getIcon())).getText());
        name.setText(product.getName());
        price.setText("CHF " + product.getPrice());
        description.setText(product.getDescription());
        setGraphic(grid);
    }

}
