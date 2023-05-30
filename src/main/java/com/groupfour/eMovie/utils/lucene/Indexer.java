package com.groupfour.eMovie.utils.lucene;

import com.groupfour.eMovie.dao.MovieDao;
import com.groupfour.eMovie.entity.Movie;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Indexer {

    public void indexAdd(MovieDao movieDao, String indexPath) throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexPath));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, config);

        List<Movie> movieList = movieDao.getMovies();
        for (Movie movie: movieList) {
            Document document = new Document();
            document.add(new IntPoint("id", movie.getId()));
            document.add(new StoredField("id", movie.getId())); // 用于存储，但不进行搜索
            document.add(new TextField("originalTitle", movie.getOriginalTitle(), Field.Store.YES));
            document.add(new StoredField("budget", movie.getBudget())); // 用于存储，但不进行搜索
            document.add(new StoredField("originalLanguage", movie.getOriginalLanguage() == null ? "" : movie.getOriginalLanguage())); // 用于存储，但不进行搜索
            document.add(new StoredField("popularity", movie.getPopularity())); // 用于存储，但不进行搜索
            document.add(new StoredField("releaseDate", movie.getReleaseDate() == null ? "" : movie.getReleaseDate())); // 用于存储，但不进行搜索
            document.add(new StoredField("revenue", movie.getRevenue())); // 用于存储，但不进行搜索
            document.add(new StoredField("runtime", movie.getRuntime())); // 用于存储，但不进行搜索
            document.add(new StoredField("title", movie.getTitle() == null ? "" : movie.getTitle())); // 用于存储，但不进行搜索
            document.add(new StoredField("voteAverage", movie.getVoteAverage())); // 用于存储，但不进行搜索
            document.add(new StoredField("voteCount", movie.getVoteCount())); // 用于存储，但不进行搜索
            document.add(new StoredField("overview", movie.getOverview() == null ? "" : movie.getOverview())); // 用于存储，但不进行搜索
            writer.addDocument(document);
        }

        writer.close();
    }

    public List<Movie> indexSearch(String keyword, String indexPath, int maxEdits) {
        List<Movie> movieList = new ArrayList<>();

        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            IndexReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);


            // 使用MultiFieldQueryParser进行多字段搜索
            // String[] searchFields = {"originalTitle"}; // 可以添加其他字段进行搜索
//            QueryParser queryParser = new MultiFieldQueryParser(searchFields, new StandardAnalyzer());
//            QueryParser queryParser = new QueryParser("originalTitle", new StandardAnalyzer());
//            Query query = queryParser.parse(keyword);

            Term term = new Term("originalTitle", keyword);
            Query query = new FuzzyQuery(term, maxEdits);

            int hitsPerPage = 16;
            TopDocs topDocs = searcher.search(query, hitsPerPage);
            ScoreDoc[] hits = topDocs.scoreDocs;

            // 遍历搜索结果
            for (ScoreDoc hit: hits) {
                Document doc = searcher.doc(hit.doc);
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(doc.get("id")));
                movie.setBudget(Integer.parseInt(doc.get("budget")));
                movie.setOriginalTitle(doc.get("originalTitle"));
                movie.setOverview(doc.get("overview"));
                movie.setOriginalLanguage(doc.get("originalLanguage"));
                movie.setTitle(doc.get("title"));
                movie.setReleaseDate(doc.get("releaseDate"));
                movie.setPopularity(Float.parseFloat(doc.get("popularity")));
                movie.setRuntime(Integer.parseInt(doc.get("runtime")));
                movie.setRevenue(Long.parseLong(doc.get("revenue")));
                movie.setVoteAverage(Float.parseFloat(doc.get("voteAverage")));
                movie.setVoteCount(Integer.parseInt(doc.get("voteCount")));
                movieList.add(movie);
            }
            reader.close();
            directory.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return movieList;
        }
    }
}
