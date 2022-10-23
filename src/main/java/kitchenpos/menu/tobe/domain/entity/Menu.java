package kitchenpos.menu.tobe.domain.entity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import kitchenpos.common.name.Name;
import kitchenpos.common.price.Price;
import kitchenpos.menu.tobe.domain.service.MenuDisplayPolicy;
import kitchenpos.menu.tobe.domain.vo.MenuGroup;
import kitchenpos.menu.tobe.domain.vo.MenuProduct;

public class Menu {

    public final UUID id;

    public final Name name;

    public final MenuGroup menuGroup;

    private final List<MenuProduct> menuProducts;

    private Boolean isVisible;

    private Price price;

    public Menu(
        final UUID id,
        final Name name,
        final Boolean isVisible,
        final Price price,
        final MenuGroup menuGroup,
        final List<MenuProduct> menuProducts
    ) {
        this.id = id;
        this.name = name;
        this.isVisible = isVisible;
        this.price = price;
        this.menuGroup = menuGroup;
        this.menuProducts = menuProducts;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public Price price() {
        return this.price;
    }

    public List<MenuProduct> menuProducts() {
        return Collections.unmodifiableList(this.menuProducts);
    }

    public void display() {
        if (!MenuDisplayPolicy.isDisplayable(this)) {
            throw new IllegalStateException("메뉴 노출 정책에 따라 이 메뉴를 노출할 수 없습니다");
        }
        this.isVisible = true;
    }

    public void hide() {
        this.isVisible = false;
    }

    public void setPrice(final Price price) {
        if (!MenuDisplayPolicy.isDisplayable(this)) {
            this.hide();
        }
        this.price = price;
    }
}
