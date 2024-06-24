package servlet;

import java.io.IOException;
import java.util.List;

import bean.Sales;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowCartServlet
 * Handles the display and management of the shopping cart.
 */
@WebServlet("/showCart")
public class ShowCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request to show the cart and remove items if specified.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        String cmd = "";

        try {
            // Retrieve the index of the item to be removed from the cart
            String delno = request.getParameter("delno");
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            List<Sales> cartItems = (List<Sales>) session.getAttribute("salesList");

            // If an index is specified and the cart has items
            if (delno != null && cartItems != null) {
                try {
                    int index = Integer.parseInt(delno);
                    if (index >= 0 && index < cartItems.size()) {
                        // Remove the item at the specified index from the cart
                        cartItems.remove(index);
                        session.setAttribute("salesList", cartItems);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    error = "Error occurred while removing the item.";
                    cmd = "showCart";
                }
            }

            // Set the cart item list as a request attribute
            request.setAttribute("cartItems", cartItems);

        } catch (Exception e) {
            e.printStackTrace();
            error = "Error occurred while displaying the cart.";
            cmd = "showCart";
        } finally {
            // Error handling
            if (error.isEmpty()) {
                // Forward to the cart display page if no errors
                request.getRequestDispatcher("/view/ShowCart.jsp").forward(request, response);
            } else {
                // Forward to the error page if there are errors
                request.setAttribute("error", error);
                request.setAttribute("cmd", cmd);
                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
            }
        }
    }

    /**
     * Handles the HTTP POST request by forwarding it to the doGet method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
