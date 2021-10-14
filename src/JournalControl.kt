import java.util.*

fun main() {
    val journalBody = JournalBody()
    val inputConsole = Scanner(System.`in`)
    var kSon:Int

    while (true){
        println("--------->MENU<---------")
        println("""
            1 -> Baxo qo'yish va yo'qlama
            2 -> Sinf ro'yxati
            3 -> Sozlamalar
        """.trimIndent())
        print("-> ")
        kSon = inputConsole.nextInt()

        when(kSon){
            1->journalBody.groupActions()
            2->journalBody.list()
            3->{
                println("--------->SOZLAMALAR<---------")
                println("""
                    1 -> Bola qo'shish
                    2 -> Bolani o'chirish
                    3 -> Sinf qo'shish
                """.trimIndent())
                kSon = inputConsole.nextInt()

                when(kSon){
                    1->journalBody.addMan()
                    2->journalBody.removeMan()
                    3->journalBody.addGroup()
                    else-> println("Noto'g'ri buyruq kiritildi!!!")
                }
            }
            else-> println("Noto'g'ri buyruq kiritildi!!!")
        }
    }
}