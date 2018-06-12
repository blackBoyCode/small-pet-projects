import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLUListElement
import kotlin.browser.document





fun main(args: Array<String>) {

    // print inside the console
    println("everything ok!")

    //get the html element (button in this case)
    var submitButton = document.getElementById("submit-btn") as HTMLButtonElement

    // add a click event listener and do stuff
    submitButton.addEventListener("click", {


        submit()
        println("button pressed")


    })



    var materialButton =  document.getElementById("button") as HTMLButtonElement
    materialButton.addEventListener("click",{

        println("button pressed two")
    })




}


fun submit(){

    var unorderedList = document.getElementById("issue-posted") as HTMLUListElement

    var listItem = document.createElement("li")

    listItem.innerHTML = "created listItem"

    unorderedList.appendChild(listItem)

}

fun doSomething(){
//
//    var h1Tag = document.
//
//    document.body?.appendChild()

}
