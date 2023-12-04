const deleteBookById = async (bookId, bookshelfId) => {
  try {
    await axios.delete(`/bookshelves/${bookshelfId}/book/${bookId}`);

    window.location.reload();
  } catch (err) {
    console.error(err);
  }
};
