package com.example.abdullahabdeen.hw2.model

import com.example.abdullahabdeen.abdullah_abdeen_hw2.model.Book

object BookSys {
    var bookList: ArrayList<Book> = ArrayList()

    fun prepareData() {
        bookList.addAll(listOf(
            Book(title = "1984", author = "George Orwell", coverImg = "https://mir-s3-cdn-cf.behance.net/project_modules/1400/b468d093312907.5e6139cf2ab03.png"),
            Book(title = "To Kill a Mockingbird", author = "Harper Lee", coverImg = "https://m.media-amazon.com/images/I/81aY1lxk+9L._AC_UF1000,1000_QL80_.jpg"),
            Book(title = "The Great Gatsby", author = "F. Scott Fitzgerald", coverImg = "https://images.booksense.com/images/386/846/9781949846386.jpg"),
            Book(title = "One Hundred Years of Solitude", author = "Gabriel Garcia Marquez", coverImg = "https://s26162.pcdn.co/wp-content/uploads/2018/02/12084166.jpg"),
            Book(title = "A Passage to India", author = "E.M. Forster", coverImg = "https://m.media-amazon.com/images/I/7187ElQvxyL._AC_UF1000,1000_QL80_.jpg"),
            Book(title = "Invisible Man", author = "Ralph Ellison", coverImg = "https://s26162.pcdn.co/wp-content/uploads/2019/04/14002331853.jpg"),
            Book(title = "Pride and Prejudice", author = "Jane Austen", coverImg = "https://images.squarespace-cdn.com/content/v1/58c180edff7c50dd0e51a2ad/1596041993633-UW2GTN4JZP8XLPZKKXCJ/9781847493699.jpg"),
            Book(title = "Catch-22", author = "Joseph Heller", coverImg = "https://d28hgpri8am2if.cloudfront.net/book_images/cvr9781451621174_9781451621174_hr.jpg"),
            Book(title = "The Catcher in the Rye", author = "J.D. Salinger", coverImg = "https://cdn.britannica.com/94/181394-050-2F76F7EE/Reproduction-cover-edition-The-Catcher-in-the.jpg"),
            Book(title = "Brave New World", author = "Aldous Huxley", coverImg = "https://m.media-amazon.com/images/I/91e-zS-ZoXL._AC_UF1000,1000_QL80_.jpg"),
            Book(title = "Fahrenheit 451", author = "Ray Bradbury", coverImg = "https://www.arts.gov/sites/default/files/Fahrenheit%20451%20Cover.jpg"),
            Book(title = "Moby Dick", author = "Herman Melville", coverImg = "https://dwcp78yw3i6ob.cloudfront.net/wp-content/uploads/2016/12/09134941/Moby-Dick.jpg")
        ))
    }

    fun addBook(book: Book) {
        bookList.add(book)
    }

    fun getBooks(): ArrayList<Book> {
        return bookList
    }
}
