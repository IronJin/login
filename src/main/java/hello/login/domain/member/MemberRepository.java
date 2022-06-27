package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member = {}", member);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {

        for (Member member : findAll()) {
            if(member.getLoginId().equals(loginId)){
                return Optional.of(member);
            }
        }

        return Optional.empty();
    }

    public List<Member> findAll() {
        List<Member> members = new ArrayList<>(store.values());
        return members;
    }

    public void clearStore() {
        store.clear();
    }

}
