package kwthon_1team.kwthon.converter;

import kwthon_1team.kwthon.domian.dto.response.SearchResponse;
import kwthon_1team.kwthon.domian.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberConverter {

    public SearchResponse toSearchResponse(Member member){
        return SearchResponse.builder()
                .studentId(member.getStudentId())
                .department(member.getDepartment())
                .name(member.getName())
                .build();
    }

}
