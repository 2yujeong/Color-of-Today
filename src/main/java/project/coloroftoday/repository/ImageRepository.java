package project.coloroftoday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.coloroftoday.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
