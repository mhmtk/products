package com.mhmt.products.domain;

import java.util.ArrayList;

public class Category {

  private int id;
  private String name;
  private String description;
  private ArrayList<Product> products;

  public Category() {

  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public void setProducts(final ArrayList<Product> products) {
    this.products = products;
  }
}
