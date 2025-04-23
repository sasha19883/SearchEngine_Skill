package main.searchengine.repozitories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.searchengine.model.SitePage;
@Repository
public interface SiteRepository extends JpaRepository<SitePage, Long> {
    SitePage findByUrl(String url);
    SitePage findByUrl(long id);
    SitePage findByUrl(SitePage site);

}
