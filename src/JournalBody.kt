/**
 * X.A Technologies, 2021
 * Creator -> Xakimov Abubakir (c)
 * Create data -> 12.10.2021
 * Update data -> 14.10.2021
 */
import java.io.File
import java.io.PrintWriter
import java.util.*
import kotlin.collections.ArrayList

class JournalBody:Plan{

    val journalList = ArrayList<DataMan>()
    val groupNames = ArrayList<String>()
    val inputConsole = Scanner(System.`in`)

    init {
        val file = File("groupNames.txt")
        val inputFile = Scanner(file)

        while (inputFile.hasNextLine()){
            groupNames.add(inputFile.nextLine())
        }

        inputFile.close()
    }

    override fun fileRead(groupName:String) {
        val file = File(groupName)
        val inputFile = Scanner(file)

        while (inputFile.hasNextLine()){
            val dataMan = DataMan()
            dataMan.SurnameName=inputFile.nextLine()
            dataMan.ballVisit=inputFile.nextLine()
            journalList.add(dataMan)
        }

        inputFile.close()
    }

    override fun fileWrite(groupName:String) {
        val file = File(groupName)
        val write = PrintWriter(file)

        for (man in journalList){
            write.println(man.SurnameName)
            write.println(man.ballVisit)
        }

        write.close()
    }

    override fun fileWriteGroupNames() {
        val file = File("groupNames.txt")
        val write = PrintWriter(file)

        for (name in groupNames){
            write.println(name)
        }

        write.close()
    }

    override fun addMan() {
        println("--------->Bola qo'shish<---------")
        var groupNumEnter:Int
        while (true) {
            groupNames.forEachIndexed { index, s ->
                println("${index + 1}. $s")
            }
            print("Sinf raqamini tanlang: ")
            groupNumEnter = inputConsole.nextInt()-1

            if (groupNames.size-1 < groupNumEnter || groupNumEnter<0) {
                println("Bunday raqamli sinf mavjud emas!!!\n")
            } else {
                break
            }
        }

        fileRead(groupNames[groupNumEnter]+".txt")

        val dataMan = DataMan()
        print("Familiya va ism kiriting (Masalan: Hakimov Abubakir): ")
        dataMan.SurnameName=readLine()
        journalList.add(dataMan)
        fileWrite(groupNames[groupNumEnter]+".txt")
        journalList.clear()
        println("Muvaffaqiyatli qo'shildi!!!")
    }

    override fun removeMan() {
        println("--------->Bolani o'chirish<---------")
        var groupNumEnter:Int
        var manNumEnter:Int
        while (true) {
            groupNames.forEachIndexed { index, s ->
                println("${index + 1}. $s")
            }
            print("Sinf raqamini tanlang: ")
            groupNumEnter = inputConsole.nextInt()-1

            if (groupNames.size-1 < groupNumEnter || groupNumEnter<0) {
                println("Bunday raqamli sinf mavjud emas!!!\n")
            } else {
                break
            }
        }

        fileRead(groupNames[groupNumEnter]+".txt")

        while (true) {
            println("--------->${groupNames[groupNumEnter]} sinf<---------")
            journalList.forEachIndexed { index, dataMan ->
                println("${index + 1}. ${dataMan.SurnameName}")
            }

            print("Kerakli bolani raqamini tanlang: ")
            manNumEnter = inputConsole.nextInt() - 1

            if (journalList.size - 1 < manNumEnter || manNumEnter < -1) {
                println("Bunday raqamli bola mavjud emas!!!\n")
            }else{
                break
            }
        }

        journalList.removeAt(manNumEnter)
        fileWrite(groupNames[groupNumEnter]+".txt")
        journalList.clear()
        println("Muvaffaqiyatli o'chirildi!!!")
    }

    override fun addGroup() {
        println("--------->Sinf qo'shish<---------")
        var groupName:String
        while (true) {
            print("Sinf nomini kiriting (5-A, 7-B, 2-C): ")
            groupName = inputConsole.next().uppercase()

            if (groupNames.contains(groupName)){
                println("Bunday sinf ro'yxatdan o'tgan! Iltimos boshqa nom kiriting!\n")
            }else{
                break
            }
        }

        var enter = false
        var nameEnter:String
        while (true){
            val dataMan = DataMan()
            print("Familiya va ism kiriting (Masalan: Hakimov Abubakir)(0-Chiqish): ")
            nameEnter= readLine()!!

            if (nameEnter=="0"){
                break
            }else{
                dataMan.SurnameName=nameEnter
                enter=true
            }
            journalList.add(dataMan)
        }

        if (enter) {
            groupNames.add(groupName)
            fileWriteGroupNames()

            val file = File("$groupName.txt")
            file.createNewFile()

            fileWrite("$groupName.txt")
            journalList.clear()

            println("$groupName sinf jurnali muvaffaqiyatli shakllantirildi!!!")
        }else{
            println("Sinf yaratilmadi!!!")
        }
    }

    override fun groupActions() {
        println("--------->Baxo qo'yish va yo'qlama<---------")
        var groupNumEnter:Int
        var manNumEnter:Int
        var ballVisitEnter:String
        while (true) {
            groupNames.forEachIndexed { index, s ->
                println("${index + 1}. $s")
            }
            print("Sinf raqamini tanlang: ")
            groupNumEnter = inputConsole.nextInt()-1

            if (groupNames.size-1 < groupNumEnter || groupNumEnter<0) {
                println("Bunday raqamli sinf mavjud emas!!!\n")
            } else {
               break
            }
        }

        fileRead(groupNames[groupNumEnter] + ".txt")

        var work=true
        while (work) {
            while (true) {
                println("--------->${groupNames[groupNumEnter]} sinf<---------")
                journalList.forEachIndexed { index, dataMan ->
                    println("${index + 1}. ${dataMan.SurnameName} | ${dataMan.ballVisit}")
                }

                print("Kerakli bolani raqamini tanlang (0-Chiqish): ")
                manNumEnter = inputConsole.nextInt() - 1

                if (journalList.size - 1 < manNumEnter || manNumEnter < -1) {
                    println("Bunday raqamli bola mavjud emas!!!\n")
                } else if(manNumEnter==-1) {
                    journalList.clear()
                    work=false
                    break
                }else{
                    break
                }
            }

            while (work) {
                println("\n${journalList[manNumEnter].SurnameName} | ${journalList[manNumEnter].ballVisit}")
                print("Baxo yoki yo'qlamani kiriting (2,3,4,5)(+,-): ")
                ballVisitEnter = inputConsole.next()

                if (ballVisitEnter == "2" || ballVisitEnter == "3" || ballVisitEnter == "4"
                    || ballVisitEnter == "5" || ballVisitEnter == "+" || ballVisitEnter == "-"
                ) {
                    if (journalList[manNumEnter].ballVisit==""){
                        journalList[manNumEnter].ballVisit+=ballVisitEnter
                    }else{
                        journalList[manNumEnter].ballVisit+=" $ballVisitEnter"
                    }
                    fileWrite(groupNames[groupNumEnter]+".txt")
                    println("Baxo yoki yo'qlama muvaffaqiyatli qo'yildi!!!\n")
                    break
                } else {
                    println("Siz noto'g'ri baxo yoki yo'qlama qo'ydingiz!!!\n")
                }
            }
        }
    }

    override fun list() {
        println("--------->Sinf ro'yxati<---------")
        var groupNumEnter:Int
        while (true) {
            groupNames.forEachIndexed { index, s ->
                println("${index + 1}. $s")
            }
            print("Sinf raqamini tanlang: ")
            groupNumEnter = inputConsole.nextInt()-1

            if (groupNames.size-1 < groupNumEnter || groupNumEnter<0) {
                println("Bunday raqamli sinf mavjud emas!!!\n")
            } else {
                break
            }
        }

        fileRead(groupNames[groupNumEnter]+".txt")

        println("--------->${groupNames[groupNumEnter]} sinf ro'yxati<---------")
        journalList.forEachIndexed { index, dataMan ->
            println("${index+1}. ${dataMan.SurnameName}")
        }

        journalList.clear()
    }
}