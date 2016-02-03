package com.example.markez.sugarormreview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.markez.sugarormreview.R;
import com.example.markez.sugarormreview.model.Address;
import com.example.markez.sugarormreview.model.Author;
import com.example.markez.sugarormreview.model.AuthorEditorial;
import com.example.markez.sugarormreview.model.Book;
import com.example.markez.sugarormreview.model.Editorial;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TUTORIAL-SUGAR-ORM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *  Creación, guardado y borrado
         */
        Log.i(TAG, "Creando una instancia de libro");
        Book cleanCode = new Book("Código limpio", "1º Edición");

        Log.i(TAG, "Instancia sin guardar. Id cleanCode: " + cleanCode.getId());
        cleanCode.save();
        Log.i(TAG, "Instancia guardada. Id cleanCode: " + cleanCode.getId());
        cleanCode.delete();
        Log.i(TAG, "Instancia eliminada. Numero de libros: " + Book.listAll(Book.class).size());

        /**
         *  Relaciones OneToMany
         */
        Book ios9 = new Book("iOS 9", "3º Edición");
        Book androidDeveloper = new Book("Android developer", "1º Edición");
        Author author1 = new Author("Markez", "Soft");
        ios9.setAuthor(author1);
        androidDeveloper.setAuthor(author1);

        // Para guardar la relación OneToMany primero debemos guardar la instancia de Author
        // y después guardar las instancias de Book. Esto es para que en el momento de guardar
        // los datos de un libro tengamos el id del autor, que se asigna al guardarlo, para tener
        // una regerencia a dicho id
        Log.i(TAG, "Creando varias instancias de libro y una de autor realcionadas");
        author1.save();
        ios9.save();
        androidDeveloper.save();

        Author authorDB = Author.findById(Author.class, author1.getId());
        Log.i(TAG, "Recuperando valores de base de datos");
        Log.i(TAG, "Author: " + authorDB.getName() + " " + authorDB.getSurname());
        for(Book b : authorDB.getBooks()){
            Log.i(TAG, "Libro: " + b.getTitle() + "-" + b.getEdition());
        }

        /**
         *  Relaciones OneToOne
         */
        // Las relaciones OneToOne unidireccionales son fáciles de usar ya que las podemos tratar
        // como una realción OneToMany. Pero la realación bidireccional no esta soportada todavía
        // por la versión 1.4. Si intentas hacerlo cuando busques uno de los dos objetos relacionados
        // bidireccionalmente entrará en un buble infito y provocará un java.lang.StackOverflowError
        Log.i(TAG, "Relaciono OneToOne unidireccional entre autor y direccion");
        Address address = new Address("C/ Sin sentido");
        address.save();
        author1.setAddress(address);
        author1.save();


        /**
         *  Relaciones ManyToMany
         */
        // En la documentación oficial no se menciona nada sobre este tipo de relaciones pero podemos
        // desglosarla en dos relaciones OneToMany añadiendo una clase intermedia
        Log.i(TAG, "Relaciono ManyToMany");
        Author author2 = new Author("Pablo", "Benavente");
        Editorial editorial1 = new Editorial("Anaya");
        Editorial editorial2 = new Editorial("Planeta");
        AuthorEditorial authorEditorial1 = new AuthorEditorial(author1, editorial1);
        AuthorEditorial authorEditorial2 = new AuthorEditorial(author2, editorial1);
        AuthorEditorial authorEditorial3 = new AuthorEditorial(author2, editorial2);
        // Primero almacenamos los autores y las editoriales
        author2.save();
        editorial1.save();
        editorial2.save();
        // Ahora almacenamos la entidad AuthorEditorial
        authorEditorial1.save();
        authorEditorial2.save();
        authorEditorial3.save();

        // Vamos a imprimir los autores de la editorial1 y las editoriales del author2
        Log.i(TAG, "Autores de la editorial 1");
        for (Author author : editorial1.getAuthors()){
            Log.i(TAG, "Author: " + author.getName() + " " + author.getSurname());
        }
        Log.i(TAG, "Editoriales del autor 2");
        for (Editorial editorial : author2.getEditorials()){
            Log.i(TAG, "Editorial: " + editorial.getName());
        }
    }
}
