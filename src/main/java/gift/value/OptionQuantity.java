package gift.value;

public class OptionQuantity {
    private final int quantity;

    public OptionQuantity(int quantity) {
        if (quantity < 1 || quantity >= 1_000_000_000) {
            throw new IllegalArgumentException("옵션 수량은 1개 이상 1억 개 미만 가능합니다.");
        }
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public OptionQuantity subtract(int quantityToSubtract) {
        if (quantityToSubtract < 0) {
            throw new IllegalArgumentException("수량은 음수가 될 수 없습니다.");
        }
        if (this.quantity - quantityToSubtract < 0) {
            throw new IllegalArgumentException("수량이 부족합니다.");
        }
        return new OptionQuantity(this.quantity - quantityToSubtract);
    }
}