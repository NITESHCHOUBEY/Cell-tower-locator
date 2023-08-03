import java.util.Scanner;

public class PointQuadtree {


    public PointQuadtreeNode root;

    public PointQuadtree() {
        this.root = null;
    }

    public boolean helper_insert(CellTower a, PointQuadtreeNode base, PointQuadtreeNode temp) {
        if (base.celltower == a) {
            return false;
        }
        if (temp.celltower.x < base.celltower.x && temp.celltower.y > base.celltower.y) {
//                System.out.println("hi");
            if (base.quadrants[0] == null) {
                base.quadrants[0] = temp;
                return true;
            }
            return helper_insert(a, base.quadrants[0], temp);
        } else if (temp.celltower.x > base.celltower.x && temp.celltower.y > base.celltower.y) {
            if (base.quadrants[1] == null) {
                base.quadrants[1] = temp;
                return true;
            }
            return helper_insert(a, base.quadrants[1], temp);
        } else if (temp.celltower.x < base.celltower.x && temp.celltower.y < base.celltower.y) {
            if (base.quadrants[2] == null) {
                base.quadrants[2] = temp;
                return true;
            }
            return helper_insert(a, base.quadrants[2], temp);
        } else if (temp.celltower.x > base.celltower.x && temp.celltower.y < base.celltower.y) {
            if (base.quadrants[3] == null) {
                base.quadrants[3] = temp;
                return true;
            }
            return helper_insert(a, base.quadrants[3], temp);
        } else if (temp.celltower.x > base.celltower.x) {
            if (base.quadrants[3] == null) {
                base.quadrants[3] = temp;
                return true;
            }
            return helper_insert(a, base.quadrants[3], temp);
        } else if (temp.celltower.x < base.celltower.x) {
            if (base.quadrants[0] == null) {
                base.quadrants[0] = temp;
                return true;
            }
            return helper_insert(a, base.quadrants[0], temp);
        } else if (temp.celltower.y > base.celltower.y) {
            if (base.quadrants[1] == null) {
                base.quadrants[1] = temp;
                return true;
            }
            return helper_insert(a, base.quadrants[1], temp);
        } else if (temp.celltower.y < base.celltower.y) {
            if (base.quadrants[2] == null) {
                base.quadrants[2] = temp;
                return true;
            }
            return helper_insert(a, base.quadrants[2], temp);
        }
        return false;
    }

    public boolean insert(CellTower a) {
//        System.out.println("insert check");
        // TO be completed by students
        PointQuadtreeNode temp = new PointQuadtreeNode(a);
        if (root == null) {
            root = temp;
            return true;
        }
        return helper_insert(a, root, temp);
    }

    public boolean helper_cellTowerAt(int x, int y, PointQuadtreeNode temp) {
        if (temp.celltower.x == x && temp.celltower.y == y) {
            return true;
        }
        if (temp.celltower.x > x && temp.celltower.y < y) {
//                System.out.println("hi");
            if (temp.quadrants[0] == null) {
//                    base.quadrants[0]=temp;
                return false;
            }
            return helper_cellTowerAt(x, y, temp.quadrants[0]);
        } else if (temp.celltower.x < x && temp.celltower.y < y) {
//                System.out.println("check");
            if (temp.quadrants[1] == null) {
//                    base.quadrants[1]=temp;
                return false;
            }
            return helper_cellTowerAt(x, y, temp.quadrants[1]);
        } else if (temp.celltower.x > x && temp.celltower.y > y) {
            if (temp.quadrants[2] == null) {
//                    base.quadrants[2]=temp;
                return false;
            }
            return helper_cellTowerAt(x, y, temp.quadrants[2]);
        } else if (temp.celltower.x < x && temp.celltower.y > y) {
            if (temp.quadrants[3] == null) {
//                    base.quadrants[3]=temp;
                return false;
            }
            return helper_cellTowerAt(x, y, temp.quadrants[3]);
        } else if (temp.celltower.x < x) {
            if (temp.quadrants[3] == null) {
//                    base.quadrants[3]=temp;
                return false;
            }
            return helper_cellTowerAt(x, y, temp.quadrants[3]);
        } else if (temp.celltower.x > x) {
            if (temp.quadrants[0] == null) {
//                    base.quadrants[0]=temp;
                return false;
            }
            return helper_cellTowerAt(x, y, temp.quadrants[0]);
        } else if (temp.celltower.y < y) {
            if (temp.quadrants[1] == null) {
//                    base.quadrants[1]=temp;
                return false;
            }
            return helper_cellTowerAt(x, y, temp.quadrants[1]);
        } else {
            if (temp.quadrants[2] == null) {
//                    base.quadrants[2]=temp;
                return false;
            }
            return helper_cellTowerAt(x, y, temp.quadrants[2]);
        }
//            return false;
    }

    public boolean cellTowerAt(int x, int y) {
        // TO be completed by students
        return helper_cellTowerAt(x, y, root);
    }

    public CellTower helper_chooseCellTower(int x, int y, int r, PointQuadtreeNode temp, CellTower result) {
        if (x < temp.celltower.x && y > temp.celltower.y) {
            if (temp.celltower.distance(x, y) >= r) {
                if (x + r < temp.celltower.x) {
                    if (y - r > temp.celltower.y) {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
//                            else {
//                                return null;
//                            }
                    } else {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[2] != null) {
                            if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                                result = temp.quadrants[2].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                } else {
                    if (y - r > temp.celltower.y) {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
//                            else {
//                                return null;
//                            }
                    } else {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
                        if (temp.quadrants[2] != null) {
                            if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                                result = temp.quadrants[2].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
            } else {
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
            }
        }
        if (x > temp.celltower.x && y > temp.celltower.y) {
            if (temp.celltower.distance(x, y) >= r) {
                if (x - r > temp.celltower.x) {
                    if (y - r > temp.celltower.y) {
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                    else {
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
                else {
                    if (y - r > temp.celltower.y) {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                    else {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
            }
            else {
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
        }
        if (x > temp.celltower.x && y > temp.celltower.y) {
            if (temp.celltower.distance(x, y) >= r) {
                if (x - r > temp.celltower.x) {
                    if (y - r > temp.celltower.y) {
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                    else {
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
                else {
                    if (y - r > temp.celltower.y) {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                    else {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
            }
            else {
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
        }
        if (x > temp.celltower.x && y < temp.celltower.y) {
            if (temp.celltower.distance(x, y) >= r) {
                if (x - r > temp.celltower.x) {
                    if (y + r < temp.celltower.y) {
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                    else {
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
                else {
                    if (y + r < temp.celltower.y) {
                        if (temp.quadrants[2] != null) {
                            if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                                result = temp.quadrants[2].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                    else {
                        if (temp.quadrants[2] != null) {
                            if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                                result = temp.quadrants[2].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                        }
                        if (temp.quadrants[1] != null) {
                            if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                                result = temp.quadrants[1].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
            }
            else {
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
        }
        if (x < temp.celltower.x && y < temp.celltower.y) {
            if (temp.celltower.distance(x, y) >= r) {
                if (x + r < temp.celltower.x) {
                    if (y + r < temp.celltower.y) {
                        if (temp.quadrants[2] != null) {
                            if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                                result = temp.quadrants[2].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                    else {
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[2] != null) {
                            if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                                result = temp.quadrants[2].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
                else {
                    if (y + r < temp.celltower.y) {
                        if (temp.quadrants[2] != null) {
                            if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                                result = temp.quadrants[2].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                    else {
                        if (temp.quadrants[2] != null) {
                            if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                                result = temp.quadrants[2].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                        }
                        if (temp.quadrants[0] != null) {
                            if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                                result = temp.quadrants[0].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                        }
                        if (temp.quadrants[3] != null) {
                            if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                                result = temp.quadrants[3].celltower;
                            }
                            result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                        }
//                            else {
//                                return null;
//                            }
                    }
                }
            }
            else {
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
        }
//            result=new CellTower(1,1,3);
        if (x>temp.celltower.x && y==temp.celltower.y){
            if (x-r>temp.celltower.x){
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
            }
            else{
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
        }
        if (x<temp.celltower.x && y==temp.celltower.y){
            if (x+r<temp.celltower.x){
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
            else{
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
        }
        if (x==temp.celltower.x && y>temp.celltower.y){
            if (y-r>temp.celltower.y){
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
            }
            else{
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
        }
        if (x==temp.celltower.x && y<temp.celltower.y){
            if (y+r<temp.celltower.y){
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
            }
            else{
                if (temp.quadrants[0] != null) {
                    if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                        result = temp.quadrants[0].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
                }
                if (temp.quadrants[1] != null) {
                    if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                        result = temp.quadrants[1].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
                }
                if (temp.quadrants[3] != null) {
                    if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                        result = temp.quadrants[3].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
                }
                if (temp.quadrants[2] != null) {
                    if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                        result = temp.quadrants[2].celltower;
                    }
                    result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
                }
            }
        }
        if (x==temp.celltower.x && y==temp.celltower.y){
            if (temp.quadrants[0] != null) {
                if ((result == null || result.cost > temp.quadrants[0].celltower.cost) && temp.quadrants[0].celltower.distance(x,y)<r) {
                    result = temp.quadrants[0].celltower;
                }
                result=helper_chooseCellTower(x, y, r, temp.quadrants[0], result);
            }
            if (temp.quadrants[1] != null) {
                if ((result == null || result.cost > temp.quadrants[1].celltower.cost) && temp.quadrants[1].celltower.distance(x,y)<r) {
                    result = temp.quadrants[1].celltower;
                }
                result=helper_chooseCellTower(x, y, r, temp.quadrants[1], result);
            }
            if (temp.quadrants[3] != null) {
                if ((result == null || result.cost > temp.quadrants[3].celltower.cost) && temp.quadrants[3].celltower.distance(x,y)<r) {
                    result = temp.quadrants[3].celltower;
                }
                result=helper_chooseCellTower(x, y, r, temp.quadrants[3], result);
            }
            if (temp.quadrants[2] != null) {
                if ((result == null || result.cost > temp.quadrants[2].celltower.cost) && temp.quadrants[2].celltower.distance(x,y)<r) {
                    result = temp.quadrants[2].celltower;
                }
                result=helper_chooseCellTower(x, y, r, temp.quadrants[2], result);
            }
        }
        return result;
    }

    public CellTower chooseCellTower(int x, int y, int r) {
        // TO be completed by students
        CellTower fin=null;
        if (root==null){
            return null;
        }
        else{
//                System.out.println("check");
            if (root.celltower.distance(x,y)<r){
//                    System.out.println("again check");
                fin=root.celltower;
            }
            fin=helper_chooseCellTower(x,y,r,root,fin);
//                System.out.println(fin);
            return fin;
        }
    }

    public static void main(String[] args) {
        PointQuadtree a=new PointQuadtree();
        while(true){
            System.out.println("which of the following operation you want to perform : \n 1) insert a cell tower \n 2) Check existence of a cell tower \n 3) find the cheapest cell tower in given range \n enter -1 to exit ");
            Scanner sc=new Scanner( System.in);
            int c=sc.nextInt();
            sc.nextLine();
            if(c==-1){
                break;
            }
            if(c==1){
                while(true){
                    System.out.println("add x y coordinate and cost of each cell tower and enter -1 adding all the cell towers :");
                    int u=sc.nextInt();
                    if(u==-1){
                        sc.nextLine();
                        break;
                    }
                    int v=sc.nextInt();
                    int w=sc.nextInt();
                    sc.nextLine();
                    CellTower temp=new CellTower(u, v, w);
                    a.insert(temp);
                }
                // System.out.println(a.cellTowerAt(10, 2));
            }
            if(c==2){
                while(true){
                    System.out.println("add x y coordinate where you want to check cell tower and enter -1 after checking all the cell towers :");
                    int u=sc.nextInt();
                    if(u==-1){
                        sc.nextLine();
                        break;
                    }
                    int v=sc.nextInt();
                    sc.nextLine();
                    System.out.println(a.cellTowerAt(u, v)); 
                }
                // System.out.println(a.cellTowerAt(10, 2));
            }
            if(c==3){
                // while(true){
                    System.out.println("add x y coordinate and radius where you want to find cell tower:");
                    int u=sc.nextInt();
                    int v=sc.nextInt();
                    int w=sc.nextInt();
                    sc.nextLine();
                    if(a.chooseCellTower(u, v, w)==null){
                        System.out.println("NO TOWER!!");
                    }
                    else{
                        System.out.println("location : "+a.chooseCellTower(u, v, w).x+" , " + a.chooseCellTower(u, v, w).y+" cost :" + a.chooseCellTower(u, v, w).cost);

                    }
                // }
                // System.out.println(a.cellTowerAt(10, 2));
            }
        }
        
    }

}
