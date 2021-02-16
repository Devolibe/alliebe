package com.alliebe.mastersejin.factory

import android.util.Log
import com.alliebe.mastersejin.R
import com.alliebe.mastersejin.dtos.StoryChildAlbumDTO
import com.alliebe.mastersejin.dtos.StoryChildCommentDTO
import com.alliebe.mastersejin.dtos.StoryParentDTO
import kotlin.random.Random

object DataFactory {

    // to generate random numbers
    private val rand = Random(123123123L)

    // Sample titles
    private val comment = listOf("comment",
        "comment2",
        "comment3",
        "comment4",
        "comment5",
        "comment6",
        "comment7"
    )

    private val question = listOf("question1",
        "question2",
        "question3",
        "question4",
        "question5",
        "question6",
        "question7"
    )


    // Sample descriptions
    private val album = listOf(
        R.drawable.album_ex,
        R.drawable.album_ex2,
        R.drawable.album_ex3,
        R.drawable.album_ex4,
        R.drawable.album_ex5
    )


    // Returns a sample `StoryParentDTO` Object
    fun getParentList(): List<StoryParentDTO> {
        val list = ArrayList<StoryParentDTO>()
        for (i in 1..rand.nextInt(5, 10)) {
            list.add(StoryParentDTO(
                question[rand.nextInt(question.size)],getChildList(), getChildAlbumList()
            ))
            Log.d("list", "$list[i]")
        }
        return list
    }

    // Returns a sample `List<ChildDTO>` Object to populate the parent object
    private fun getChildList(): List<StoryChildCommentDTO> {
        val list = ArrayList<StoryChildCommentDTO>()
        for (i in 1..rand.nextInt(1, 4)) {
            list.add(getRandomChild())
        }
        return list
    }
    private fun getChildAlbumList(): List<StoryChildAlbumDTO> {
        val list = ArrayList<StoryChildAlbumDTO>()
        for (i in 1..rand.nextInt(1, 4)) {
            list.add(getRandomAlbumChild())
        }
        return list
    }


    // Returns a sample `ChildDTO` Object
    private fun getRandomChild(): StoryChildCommentDTO {
        return StoryChildCommentDTO(
            comment[rand.nextInt(comment.size)],  "@mipmap/img_sample"
        )
    }

    private fun getRandomAlbumChild(): StoryChildAlbumDTO {
        return StoryChildAlbumDTO(
            album[rand.nextInt(album.size)]
        )
    }
}