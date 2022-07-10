package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Album")
@Getter @Setter
public class Album extends Item {

    private String artist;
    private String etc;

    protected Album() {
    }

    public Album(String artist, String etc) {
        this.artist = artist;
        this.etc = etc;
    }
}
