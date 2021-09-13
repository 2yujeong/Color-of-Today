package project.coloroftoday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.coloroftoday.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
