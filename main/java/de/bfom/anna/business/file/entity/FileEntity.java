package de.bfom.anna.business.file.entity;

import org.apache.commons.io.FileUtils;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "FILES")
public class FileEntity {

    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "mime")
    private String mime;

    @Column(name = "created")
    private LocalDateTime created;

    @Lob
    @Column(name = "file", columnDefinition="MEDIUMBLOB")
    private byte[] file;



    @Override
    public boolean equals(Object object){
        if(equalsWithOutId(object)){
            return this.id == ((FileEntity) object).getId();
        }
        else return false;
    }

    public boolean equalsWithOutId(Object object){
        if(object.getClass() == FileEntity.class){
            FileEntity file = (FileEntity) object;
            return(this.name.equals(file.getName()) &&
                    this.mime.equals(file.getMime()) &&
                    this.created.isEqual(file.getCreated()) &&
                    Arrays.equals(this.file, file.getFile()));
        }
        else return false;
    }


    // getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    // builder

    public static Builder newFileEntity(){
        return new Builder();
    }

    private FileEntity(Builder b){
        this.setName(b.name);
        this.setMime(b.mime);
        this.setCreated(b.created);
        this.setFile(b.file);
    }
    private static FileEntity FileEntityWithID(Builder b){
        FileEntity ent = new FileEntity(b);
        ent.setId(b.id);
        return ent;
    }

    private FileEntity(){

    }

    public static final class Builder{
        private int id;
        private String name;
        private String mime;
        private LocalDateTime created;
        private byte[] file;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder mime(String mime){
            this.mime = mime;
            return this;
        }

        public Builder created(LocalDateTime created){
            this.created = created;
            return this;
        }

        public Builder file(byte[] file){
            this.file = file;
            return this;
        }

        public FileEntity build(){
            return new FileEntity(this);
        }

        public FileEntity buildWithID(){ return FileEntityWithID(this);}
    }
}
