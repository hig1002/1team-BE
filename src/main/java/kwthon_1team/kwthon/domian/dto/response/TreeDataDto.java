import lombok.*;

@Data
public class TreeDataDto {
    private int treeLevel;

    public TreeDataDto(int treeLevel) {
        this.treeLevel = treeLevel;
    }

}
