import lombok.*;

@Data
@AllArgsConstructor
public class TreeResponseDto {
    private int status;
    private String message;
    private TreeDataDto data;

    public TreeResponseDto(int status, String message, TreeDataDto data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}