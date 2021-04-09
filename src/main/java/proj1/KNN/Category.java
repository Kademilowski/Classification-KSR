package proj1.KNN;

public interface Category extends Comparable<Category>
{

  //interfejs odopowiedzialny za obiekty juz wiadomo gdzie znajdujace sie

  int compareTo(Category aCategory);  
  boolean equals(Category category);
}
