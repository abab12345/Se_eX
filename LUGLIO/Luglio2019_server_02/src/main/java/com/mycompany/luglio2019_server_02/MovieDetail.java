/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luglio2019_server_02;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Objects;

/**
 *
 * @author signorautoma
 */

@JacksonXmlRootElement(localName= "MovieDetail")
public class MovieDetail {
    
    private Integer movieID;
    private String movieTitle;
    private String movieYear;
    private String directorName;
    private String directorYearOfBirth;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.movieTitle);
        hash = 17 * hash + Objects.hashCode(this.movieYear);
        hash = 17 * hash + Objects.hashCode(this.directorName);
        hash = 17 * hash + Objects.hashCode(this.directorYearOfBirth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovieDetail other = (MovieDetail) obj;
        if (!Objects.equals(this.movieTitle, other.movieTitle)) {
            return false;
        }
        if (!Objects.equals(this.movieYear, other.movieYear)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.directorYearOfBirth, other.directorYearOfBirth)) {
            return false;
        }
        return true;
    }
    
    public MovieDetail() {
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getDirectorYearOfBirth() {
        return directorYearOfBirth;
    }

    public void setDirectorYearOfBirth(String directorYearOfBirth) {
        this.directorYearOfBirth = directorYearOfBirth;
    }

}
