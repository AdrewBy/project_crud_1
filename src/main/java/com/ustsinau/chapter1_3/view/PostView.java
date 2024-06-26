package com.ustsinau.chapter1_3.view;


import com.ustsinau.chapter1_3.controller.PostController;
import com.ustsinau.chapter1_3.models.Label;
import com.ustsinau.chapter1_3.models.Post;
import com.ustsinau.chapter1_3.repository.LabelRepository;
import com.ustsinau.chapter1_3.repository.impl.GsonLabelRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PostView {

    private final LabelRepository label = new GsonLabelRepository();
    private final Scanner scanner = new Scanner(System.in);
    private final HeadConsole headConsole = new HeadConsole();
    public static final String ACTIONS_POST = "Введите действие:\n" +
            "1.Создать новый пост\n" +
            "2.Изменить пост и добавить лэйблы\n" +
            "3.Удалить пост\n" +
            "4.Получить информацию об посте по id\n" +
            "5.Список всех постов\n";

    PostController postCont = new PostController();

    public void createPost() throws IOException {

        System.out.println("Введите название нового поста:");
        String title = scanner.nextLine();
        System.out.println("Введите содержание нового поста:");
        String content = scanner.nextLine();

        postCont.createPostWithoutLabel(title, content);
        headConsole.run();

    }

    public void updatePost() throws IOException {

        System.out.println("Введите id поста для изменения его имени:");
        long index = Long.parseLong(scanner.next());
        System.out.println("Введите новое название поста:");
        String title = scanner.next();
        System.out.println("Введите новое содержание поста:");
        String content = scanner.next();
        scanner.nextLine();
        System.out.println("Введите id лэйблов для нового поста через ПРОБЕЛ:");
        String labelsUp = scanner.nextLine();

        List<Label> labelsPost = new ArrayList<>();

        long[] numArr = Arrays.stream(labelsUp.split(" ")).mapToLong(e -> Long.parseLong(e)).toArray();

        for (int i = 0; i < numArr.length; i++) {       // подумать над этим
            labelsPost.add(label.getById(numArr[i]));
        }

        postCont.updatePost(index, title, content, labelsPost);
        headConsole.run();
    }

    public void deletePost() throws IOException {

        System.out.println("Введите id поста для его удаления:");
        long indexForDelete = Long.parseLong(scanner.next());
        postCont.deletePost(indexForDelete);
        headConsole.run();
    }

    public void getIdPost() throws IOException {

        System.out.println("Введите id поста для получения информации:");
        long id = Long.parseLong(scanner.next());
        System.out.println("\n" + postCont.getValueByIndex(id) + "\n");
        headConsole.run();
    }

    public void showAllPosts() throws IOException {
        System.out.println("Список всех постов:");
        for (Post item : postCont.showAll()) {
            System.out.println(item);
        }
        headConsole.run();
    }
}