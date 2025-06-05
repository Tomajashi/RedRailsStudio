package de.gts.redrail.game.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinSessionResponseDto {
    private boolean joined;
    private String uid;
    private String message;

    
}
