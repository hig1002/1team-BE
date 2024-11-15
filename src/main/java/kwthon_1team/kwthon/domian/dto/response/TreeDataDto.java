package kwthon_1team.kwthon.domian.dto.response;

import lombok.*;

@Data
public class TreeDataDto {
    private int treeLevel;

    public TreeDataDto(int treeLevel) {
        this.treeLevel = treeLevel;
    }

}
